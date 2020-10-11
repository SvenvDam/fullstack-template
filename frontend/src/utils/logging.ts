import {grpc} from '@improbable-eng/grpc-web';

import {LoggingService} from '../generated/logging_pb_service';
import {LogMessage, LevelMap} from '../generated/logging_pb';

export type Logger = {
  log: (level: LogLevel, message: string) => void;
  debug: (message: string) => void;
  info: (message: string) => void;
  warning: (message: string) => void;
  error: (message: string) => void;
};

export type LogLevel =
  | LevelMap['DEBUG']
  | LevelMap['INFO']
  | LevelMap['WARN']
  | LevelMap['ERROR'];

function getLogger(host: string): Logger {
  const sendLog = (request: LogMessage) =>
    grpc.invoke(LoggingService.Log, {
      request,
      host,
      onMessage: undefined, // fire and forget
      onEnd: (code, msg, trailers) => {
        if (code !== grpc.Code.OK) {
          console.log('Could not send log to backend', code, msg, trailers);
        }
      },
    });

  const log = (level: LogLevel, message: string): void => {
    const request = new LogMessage();
    request.setMessage(message);
    request.setLevel(level);
    request.setTimestamp(Math.floor(Date.now()));

    sendLog(request);
  };

  const debug = (message: string) => log(0, message);
  const info = (message: string) => log(1, message);
  const warning = (message: string) => log(2, message);
  const error = (message: string) => log(3, message);

  return {
    log,
    debug,
    info,
    warning,
    error,
  };
}

export {getLogger};

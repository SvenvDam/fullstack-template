import React from 'react';
import {getLogger} from './utils/logging';
import {getServiceHost} from './utils/grpc';

const App: React.FunctionComponent = () => {
  const logger = getLogger(getServiceHost());

  React.useEffect(() => {
    logger.info('Hello world');
  }, [logger]);

  return <div>Hello, world</div>;
};

export default App;

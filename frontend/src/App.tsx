import React from 'react';
import {getLogger} from './utils/logging';

const App: React.FunctionComponent = () => {
  const logger = getLogger('http://127.0.0.1:5000');

  React.useEffect(() => {
    logger.info('Hello world');
  }, [logger]);

  return <div>Hello, world</div>;
};

export default App;

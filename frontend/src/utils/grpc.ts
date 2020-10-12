function getServiceHost(): string {
  return process.env.NODE_ENV === 'production'
    ? `${window.location.protocol}//${window.location.hostname}:${window.location.port}` // static content served from grpc server in production
    : 'http://127.0.0.1:5000'; // CORS for development
}

export {getServiceHost};

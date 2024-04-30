import React, { useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { isAuthenticated } from '../utils/StorageUtil'

function PrivateRoute({ component: Component, children }) {

  useEffect(() => {
  }, []);

  return isAuthenticated() ? (
    <Component>{children}</Component>
  ) : (
    <Navigate to={{
      pathname: "/login",
      state: { from: window.location }
    }} />
  );
}

export default PrivateRoute;
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Feed from '../pages/Feed';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Feed />,
  },
]);

function Routes() {
  return <Feed />;
  // return <RouterProvider router={router} />;
}

export default Routes;

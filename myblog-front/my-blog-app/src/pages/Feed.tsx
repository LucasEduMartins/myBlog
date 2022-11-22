import { useEffect, useState } from 'react';

const Feed = () => {
  // const [a, ] = useState('');

  useEffect(() => {
    console.log('component Feed mounted');
  }, []);

  // const Memo = useMemo(() => {
  //   return (
  //     <>
  //       <h1>Feed</h1>
  //       <Test customValeu={a} />
  //     </>
  //   );
  // }, [a]);

  // return Memo;
  return <h1>Feed</h1>;
};

export default Feed;

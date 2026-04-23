import Layout from '@theme/Layout';
import { Features } from '../components/Features';
import { PageHero } from '../components/PageHero';
import Data from '../data/home-page.json';

const Home = (): JSX.Element => {
  return (
    <Layout title={Data.title} description={Data.description}>
      <main>
        <PageHero
          title={Data.title}
          tagLine={Data.description}
          image={Data.image}
          buttons={Data.buttons}
          gitButtons={Data.gitButtons}
        />
        <Features features={Data.features} />
      </main>
    </Layout>
  );
};

export default Home;

import React from 'react';
import Layout from '@theme/Layout';
import { Features } from '../components/Features';
import Data from '../data/home-page.json';
import { PageHero } from '../components/PageHero';
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';
import CodeBlock from '@theme/CodeBlock';
import style from './index.module.css';

const Tutorial = (): JSX.Element => {
  return (
    <section className={style.TutorialSection}>
      <Tabs>
        <TabItem value='API Testing'>
          <Tabs>
            <TabItem value='config-setup' label='1. Config Setup'>
              <CodeBlock
                className={style.CodeBlock}
                language='json'
                title='src/test/resources/boyka-config.json'
              >
                {`{
  "ui": {
    ...
  },
  "api": {
    "key_1": {
      "base_uri": "https://reqres.in",
      "base_path": "/api",
      "read_timeout": 2,
      "write_timeout": 2,
      "connection_timeout": 1,
      "logging": {
        "request": true,
        "response": true
      }
    }
  }
}`}
              </CodeBlock>
            </TabItem>
            <TabItem value='build-request' label='2. Build Request'>
              <CodeBlock
                className={style.CodeBlock}
                language='java'
                title='src/test/java/package/TestApi.java'
              >
                {`import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.manager.ApiManager.execute;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.testng.api.requests.User;
import org.testng.annotations.Test;

public class TestApi {
    @Test (description = "Test POST request for creating a new pet", priority = 1)
    public void testUserCreation () {
        // Create request body object.
        // highlight-start
        final User user = User.createUser ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .create ();
        
        // Build Request.
        final ApiRequest request = createRequest ().configKey ("key_1")
            .method (POST)
            .path ("/users")
            .bodyObject (user)
            .create ();
        // highlight-end

        // Execute Request.
        final ApiResponse response = execute (request);

        // Verify Response.
        response.verifyStatusCode ()
            .isEqualTo (201);
        response.verifyTextField ("id")
            .isNotNull ();
        response.verifyTextField ("name")
            .isEqualTo (user.getName ());
        response.verifyTextField ("job")
            .isEqualTo (user.getJob ());
        response.verifyTextField ("createdAt")
            .isNotNull ();
    }
}`}
              </CodeBlock>
            </TabItem>
            <TabItem value='execute-request' label='3. Execute Request'>
              <CodeBlock
                className={style.CodeBlock}
                language='java'
                title='src/test/java/package/TestApi.java'
              >
                {`import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.manager.ApiManager.execute;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.testng.api.requests.User;
import org.testng.annotations.Test;

public class TestApi {
    @Test (description = "Test POST request for creating a new pet", priority = 1)
    public void testUserCreation () {
        // Create request body object.
        final User user = User.createUser ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .create ();
        
        // Build Request.
        final ApiRequest request = createRequest ().configKey ("key_1")
            .method (POST)
            .path ("/users")
            .bodyObject (user)
            .create ();

        // Execute Request.
        // highlight-start
        final ApiResponse response = execute (request);
        // highlight-end

        // Verify Response.
        response.verifyStatusCode ()
            .isEqualTo (201);
        response.verifyTextField ("id")
            .isNotNull ();
        response.verifyTextField ("name")
            .isEqualTo (user.getName ());
        response.verifyTextField ("job")
            .isEqualTo (user.getJob ());
        response.verifyTextField ("createdAt")
            .isNotNull ();
    }
}`}
              </CodeBlock>
            </TabItem>
            <TabItem value='verify-response' label='4. Verify Response'>
              <CodeBlock
                className={style.CodeBlock}
                language='java'
                title='src/test/java/package/TestApi.java'
              >
                {`import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.manager.ApiManager.execute;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.testng.api.requests.User;
import org.testng.annotations.Test;

public class TestApi {
    @Test (description = "Test POST request for creating a new pet", priority = 1)
    public void testUserCreation () {
        // Create request body object.
        final User user = User.createUser ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .create ();
        
        // Build Request.
        final ApiRequest request = createRequest ().configKey ("key_1")
            .method (POST)
            .path ("/users")
            .bodyObject (user)
            .create ();

        // Execute Request.
        final ApiResponse response = execute (request);

        // Verify Response.
        // highlight-start
        response.verifyStatusCode ()
            .isEqualTo (201);
        response.verifyTextField ("id")
            .isNotNull ();
        response.verifyTextField ("name")
            .isEqualTo (user.getName ());
        response.verifyTextField ("job")
            .isEqualTo (user.getJob ());
        response.verifyTextField ("createdAt")
            .isNotNull ();
        // highlight-end
    }
}`}
              </CodeBlock>
            </TabItem>
          </Tabs>
        </TabItem>
        <TabItem value='Web Testing'>
          <Tabs>
            <TabItem value='config-setup' label='1. Config Setup'>
              <CodeBlock
                className={style.CodeBlock}
                language='json'
                title='src/test/resources/boyka-config.json'
              >
                {`{
  "ui": {
    "playback": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10
    },
    "web": {
      "test_local_chrome": {
        "browser": "CHROME",
        "headless": false
      },
      "test_local_firefox": {
        "browser": "FIREFOX"
      },
      "test_local_edge": {
        "browser": "EDGE"
      },
      "test_local_safari": {
        "browser": "SAFARI"
      },
      "test_local_opera": {
        "browser": "OPERA"
      },
      "test_browserstack_chrome": {
        "browser": "REMOTE",
        "cloud": "BROWSER_STACK",
        "protocol": "HTTPS",
        "host": "hub-cloud.browserstack.com",
        "user_name": "\${env:CLOUD_USER}",
        "password": "\${env:CLOUD_KEY}",
        "capabilities": {
          "browser": "Chrome",
          "browser_version": "latest",
          "os": "Windows",
          "os_version": "10",
          "resolution": "1920x1080",
          "project": "Test Boyka Project",
          "build": "Test BrowserStack Build",
          "name": "Test BrowserStack Session"
        }
      },
      "test_selenium_grid": {
        "browser": "REMOTE",
        "cloud": "NONE",
        "port": "4444",
        "capabilities": {
          "browserName": "chrome",
          "platform": "MAC"
        }
      }
    },
    "android": {
      ...
    },
    "ios": {
      ...
    }
  },
  "api": {
    ...
  }
}`}
              </CodeBlock>
            </TabItem>
            <TabItem value='page-object' label='2. Page Objects'>
              <CodeBlock
                className={style.CodeBlock}
                language='java'
                title='src/test/java/package/TestWeb.java'
              >
                {`import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
    public static LoginPage loginPage () {
        return new LoginPage ();
    }

    private final Locator loginBox = buildLocator ().web (id ("login_button_container"))
        .build ();
    private final Locator loginButton = buildLocator ().web (id ("login-button"))
        .parent (this.loginBox)
        .build ();
    private final Locator password = buildLocator ().web (id ("password"))
        .parent (this.loginBox)
        .build ();
    private final Locator username = buildLocator ().web (id ("user-name"))
        .parent (this.loginBox)
        .build ();

    private LoginPage () {
        // Avoid explicit class initialization.
    }
}`}
              </CodeBlock>
            </TabItem>
            <TabItem value='write-test' label='3. Write Tests'>
              <CodeBlock
                className={style.CodeBlock}
                language='java'
                title='src/test/java/package/TestWeb.java'
              >
                {`import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.ElementActions.submit;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.web.pages.LoginPage.loginPage;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.enums.ApplicationType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestWeb {
    private static final String URL = "https://www.saucedemo.com";

    @BeforeClass (description = "Setup test class")
    public void setupTestClass () {
        createDriver (ApplicationType.WEB, "test_local_chrome");
    }

    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        closeDriver ();
    }
    
    @Test (description = "Test login functionality")
    public void testLogin () {
        navigateTo (URL);
        verifyBrowserUrl ().startsWith (URL);
        enterText (loginPage ().getUsername (), "standard_user");
        enterText (loginPage ().getPassword (), "secret_sauce");
        submit (loginPage ().getLoginButton ());
        verifyBrowserUrl ().isEqualTo (format ("{0}/inventory.html", URL));
        verifyBrowserTitle ().isEqualTo ("Swag Labs");
        verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
        verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
    }
}`}
              </CodeBlock>
            </TabItem>
          </Tabs>
        </TabItem>
      </Tabs>
    </section>
  );
};

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
        <Tutorial />
      </main>
    </Layout>
  );
};

export default Home;

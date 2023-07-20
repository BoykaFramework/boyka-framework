"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[2071],{167:(e,t,n)=>{n.d(t,{Zo:()=>u,kt:()=>d});var r=n(5721);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var l=r.createContext({}),c=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},u=function(e){var t=c(e.components);return r.createElement(l.Provider,{value:t},e.children)},p="mdxType",f={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},m=r.forwardRef((function(e,t){var n=e.components,o=e.mdxType,i=e.originalType,l=e.parentName,u=s(e,["components","mdxType","originalType","parentName"]),p=c(n),m=o,d=p["".concat(l,".").concat(m)]||p[m]||f[m]||i;return n?r.createElement(d,a(a({ref:t},u),{},{components:n})):r.createElement(d,a({ref:t},u))}));function d(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var i=n.length,a=new Array(i);a[0]=m;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s[p]="string"==typeof e?e:o,a[1]=s;for(var c=2;c<i;c++)a[c]=n[c];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}m.displayName="MDXCreateElement"},3806:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>a,default:()=>f,frontMatter:()=>i,metadata:()=>s,toc:()=>c});var r=n(4715),o=(n(5721),n(167));const i={title:"\ud83e\ude9b Setup Configuration",sidebar_position:1},a=void 0,s={unversionedId:"guides/ui/web/setup-config",id:"guides/ui/web/setup-config",title:"\ud83e\ude9b Setup Configuration",description:"Before starting to automate Web applications, we need to first setup the configuration file for our Application under test.",source:"@site/docs/framework-docs/guides/ui/web/setup-config.md",sourceDirName:"guides/ui/web",slug:"/guides/ui/web/setup-config",permalink:"/boyka-framework/docs/guides/ui/web/setup-config",draft:!1,editUrl:"https://github.com/BoykaFramework/boyka-framework/edit/main/website/docs/framework-docs/guides/ui/web/setup-config.md",tags:[],version:"current",lastUpdatedBy:"Wasiq Bhamla",lastUpdatedAt:1689839076,formattedLastUpdatedAt:"Jul 20, 2023",sidebarPosition:1,frontMatter:{title:"\ud83e\ude9b Setup Configuration",sidebar_position:1},sidebar:"docs",previous:{title:"\u2705 Verify Response",permalink:"/boyka-framework/docs/guides/api/verify-response"},next:{title:"\ud83d\udcc4 Create Page Object",permalink:"/boyka-framework/docs/guides/ui/web/create-page-object"}},l={},c=[{value:"Details of each Web configurations",id:"web-config-details",level:2}],u={toc:c},p="wrapper";function f(e){let{components:t,...n}=e;return(0,o.kt)(p,(0,r.Z)({},u,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("p",null,"Before starting to automate Web applications, we need to first setup the configuration file for our Application under test."),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-json",metastring:'title="src/test/resources/boyka-config.json"',title:'"src/test/resources/boyka-config.json"'},'{\n  "ui": {\n    "timeout": {\n      "implicit_wait": 10,\n      "explicit_wait": 30,\n      "page_load_timeout": 30,\n      "script_timeout": 10\n    },\n    "logging": {\n      "exclude_logs": [\n        "bugreport"\n      ]\n    },\n    "screenshot": {\n      "enabled": true,\n      "path": "./screenshots",\n      "extension": "jpeg",\n      "prefix": "SCR"\n    },\n    "web": {\n      "test_local_chrome": {\n        "browser": "CHROME"\n      },\n      "test_local_firefox": {\n        "browser": "FIREFOX"\n      },\n      "test_local_edge": {\n        "browser": "EDGE"\n      },\n      "test_local_safari": {\n        "browser": "SAFARI"\n      },\n      "test_browserstack_chrome": {\n        "browser": "REMOTE",\n        "target": "BROWSER_STACK",\n        "user_name": "${env:BS_USER}",\n        "password": "${env:BS_KEY}",\n        "capabilities": {\n          "browser": "Chrome",\n          "browser_version": "latest",\n          "os": "Windows",\n          "os_version": "10",\n          "resolution": "1920x1080",\n          "project": "Test Boyka Project",\n          "build": "Test BrowserStack Build",\n          "name": "Test BrowserStack Session"\n        }\n      },\n      "test_selenium_grid": {\n        "browser": "REMOTE",\n        "target": "LOCAL",\n        "port": "4444",\n        "capabilities": {\n          "browserName": "chrome",\n          "platform": "MAC"\n        }\n      },\n      "test_lambda_test_chrome": {\n        "browser": "REMOTE",\n        "target": "LAMBDA_TEST_WEB",\n        "user_name": "${env:LT_USER}",\n        "password": "${env:LT_KEY}",\n        "capabilities": {\n          "browserName": "Chrome",\n          "version": "99.0",\n          "platform": "Windows 10",\n          "resolution": "1920x1080",\n          "build": "Test LambdaTest Build",\n          "name": "Test LambdaTest Session",\n          "network": true,\n          "visual": true,\n          "video": true,\n          "console": true\n        }\n      }\n    }\n  }\n}\n')),(0,o.kt)("admonition",{type:"info"},(0,o.kt)("p",{parentName:"admonition"},"To know more about Web configurations, please refer to the ",(0,o.kt)("a",{parentName:"p",href:"/docs/guides/configuration#web-config"},"Web Configuration guide"),".")),(0,o.kt)("h2",{id:"web-config-details"},"Details of each Web configurations"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_local_chrome"),": This is the configuration for running the test on local Chrome browser."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_local_firefox"),": This is the configuration for running the test on local Firefox browser."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_local_edge"),": This is the configuration for running the test on local Edge browser."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_local_safari"),": This is the configuration for running the test on local Safari browser."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_browserstack_chrome"),": This is the configuration for running the test on BrowserStack Chrome browser."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_selenium_grid"),": This is the configuration for running the test on Selenium Grid."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("inlineCode",{parentName:"li"},"test_lambda_test_chrome"),": This is the configuration for running the test on LambdaTest Chrome browser.")))}f.isMDXComponent=!0}}]);
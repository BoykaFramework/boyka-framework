"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[3145],{167:(e,t,n)=>{n.d(t,{Zo:()=>p,kt:()=>m});var r=n(5721);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}var c=r.createContext({}),u=function(e){var t=r.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},p=function(e){var t=u(e.components);return r.createElement(c.Provider,{value:t},e.children)},f="mdxType",l={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,o=e.mdxType,i=e.originalType,c=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),f=u(n),d=o,m=f["".concat(c,".").concat(d)]||f[d]||l[d]||i;return n?r.createElement(m,a(a({ref:t},p),{},{components:n})):r.createElement(m,a({ref:t},p))}));function m(e,t){var n=arguments,o=t&&t.mdxType;if("string"==typeof e||o){var i=n.length,a=new Array(i);a[0]=d;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s[f]="string"==typeof e?e:o,a[1]=s;for(var u=2;u<i;u++)a[u]=n[u];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},9864:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>c,contentTitle:()=>a,default:()=>l,frontMatter:()=>i,metadata:()=>s,toc:()=>u});var r=n(4715),o=(n(5721),n(167));const i={title:"\ud83e\ude9b Setup API Configuration",sidebar_position:1},a=void 0,s={unversionedId:"guides/api/setup-config",id:"guides/api/setup-config",title:"\ud83e\ude9b Setup API Configuration",description:"We can set multiple configurations in the configuration file with different key name for different end points.",source:"@site/docs/framework-docs/guides/api/setup-config.md",sourceDirName:"guides/api",slug:"/guides/api/setup-config",permalink:"/boyka-framework/docs/guides/api/setup-config",draft:!1,editUrl:"https://github.com/BoykaFramework/boyka-framework/edit/main/website/docs/framework-docs/guides/api/setup-config.md",tags:[],version:"current",lastUpdatedBy:"Wasiq Bhamla",lastUpdatedAt:1689839076,formattedLastUpdatedAt:"Jul 20, 2023",sidebarPosition:1,frontMatter:{title:"\ud83e\ude9b Setup API Configuration",sidebar_position:1},sidebar:"docs",previous:{title:"\ud83d\udd29 Configuration",permalink:"/boyka-framework/docs/guides/configuration"},next:{title:"\ud83c\udfd7\ufe0f Compose Request",permalink:"/boyka-framework/docs/guides/api/compose-request"}},c={},u=[],p={toc:u},f="wrapper";function l(e){let{components:t,...n}=e;return(0,o.kt)(f,(0,r.Z)({},p,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("p",null,"We can set multiple configurations in the configuration file with different key name for different end points."),(0,o.kt)("p",null,"Let's see how to set configuration in the configuration file for API end-points."),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-json",metastring:'title="src/test/resources/boyka-config.json"',title:'"src/test/resources/boyka-config.json"'},'{\n  "api": {\n    "test_reqres": {\n      "base_uri": "https://reqres.in",\n      "base_path": "/api",\n      "read_timeout": 2,\n      "write_timeout": 2,\n      "connection_timeout": 1,\n      "logging": {\n        "request": true,\n        "response": true\n      },\n      "schema_path":"schema/"\n    }\n  }\n}\n')),(0,o.kt)("admonition",{type:"info"},(0,o.kt)("p",{parentName:"admonition"},"For more information about API configurations, please refer to ",(0,o.kt)("a",{parentName:"p",href:"/docs/guides/configuration#api-config"},"API configuration guide"),".")))}l.isMDXComponent=!0}}]);
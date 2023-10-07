"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[9407],{8570:(e,t,r)=>{r.d(t,{Zo:()=>l,kt:()=>f});var n=r(79);function a(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function i(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function o(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?i(Object(r),!0).forEach((function(t){a(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):i(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function c(e,t){if(null==e)return{};var r,n,a=function(e,t){if(null==e)return{};var r,n,a={},i=Object.keys(e);for(n=0;n<i.length;n++)r=i[n],t.indexOf(r)>=0||(a[r]=e[r]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)r=i[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(a[r]=e[r])}return a}var s=n.createContext({}),p=function(e){var t=n.useContext(s),r=t;return e&&(r="function"==typeof e?e(t):o(o({},t),e)),r},l=function(e){var t=p(e.components);return n.createElement(s.Provider,{value:t},e.children)},u="mdxType",d={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},m=n.forwardRef((function(e,t){var r=e.components,a=e.mdxType,i=e.originalType,s=e.parentName,l=c(e,["components","mdxType","originalType","parentName"]),u=p(r),m=a,f=u["".concat(s,".").concat(m)]||u[m]||d[m]||i;return r?n.createElement(f,o(o({ref:t},l),{},{components:r})):n.createElement(f,o({ref:t},l))}));function f(e,t){var r=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=r.length,o=new Array(i);o[0]=m;var c={};for(var s in t)hasOwnProperty.call(t,s)&&(c[s]=t[s]);c.originalType=e,c[u]="string"==typeof e?e:a,o[1]=c;for(var p=2;p<i;p++)o[p]=r[p];return n.createElement.apply(null,o)}return n.createElement.apply(null,r)}m.displayName="MDXCreateElement"},4004:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>s,contentTitle:()=>o,default:()=>d,frontMatter:()=>i,metadata:()=>c,toc:()=>p});var n=r(3262),a=(r(79),r(8570));const i={title:"ApiActions",sidebar_position:1},o=void 0,c={unversionedId:"actions/api/api-actions",id:"actions/api/api-actions",title:"ApiActions",description:"Static methods",source:"@site/docs/api/actions/api/api-actions.md",sourceDirName:"actions/api",slug:"/actions/api/api-actions",permalink:"/boyka-framework/api/actions/api/api-actions",draft:!1,editUrl:"https://github.com/BoykaFramework/boyka-framework/edit/main/website/docs/api/actions/api/api-actions.md",tags:[],version:"current",lastUpdatedBy:"Wasiq Bhamla",lastUpdatedAt:1696686479,formattedLastUpdatedAt:"Oct 7, 2023",sidebarPosition:1,frontMatter:{title:"ApiActions",sidebar_position:1},sidebar:"api",previous:{title:"Introduction",permalink:"/boyka-framework/api/intro"},next:{title:"AndroidDeviceActions",permalink:"/boyka-framework/api/actions/device/android-device-actions"}},s={},p=[{value:"Static methods",id:"static-methods",level:2},{value:"<code>withRequest</code>",id:"withrequest",level:3}],l={toc:p},u="wrapper";function d(e){let{components:t,...r}=e;return(0,a.kt)(u,(0,n.Z)({},l,r,{components:t,mdxType:"MDXLayout"}),(0,a.kt)("h2",{id:"static-methods"},"Static methods"),(0,a.kt)("h3",{id:"withrequest"},(0,a.kt)("inlineCode",{parentName:"h3"},"withRequest")),(0,a.kt)("p",null,"This method takes in ",(0,a.kt)("a",{parentName:"p",href:"/api/builders/api-request"},(0,a.kt)("inlineCode",{parentName:"a"},"ApiRequest"))," instance and returns ",(0,a.kt)("a",{parentName:"p",href:"/api/builders/api-response"},(0,a.kt)("inlineCode",{parentName:"a"},"IApiActions"))," instance."),(0,a.kt)("pre",null,(0,a.kt)("code",{parentName:"pre",className:"language-java"},"final IApiActions response = ApiManager.withRequest (request);\n")))}d.isMDXComponent=!0}}]);
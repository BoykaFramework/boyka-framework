"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[8579],{8570:(e,t,n)=>{n.d(t,{Zo:()=>d,kt:()=>m});var r=n(79);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,i=function(e,t){if(null==e)return{};var n,r,i={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var c=r.createContext({}),l=function(e){var t=r.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},d=function(e){var t=l(e.components);return r.createElement(c.Provider,{value:t},e.children)},p="mdxType",u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},f=r.forwardRef((function(e,t){var n=e.components,i=e.mdxType,o=e.originalType,c=e.parentName,d=s(e,["components","mdxType","originalType","parentName"]),p=l(n),f=i,m=p["".concat(c,".").concat(f)]||p[f]||u[f]||o;return n?r.createElement(m,a(a({ref:t},d),{},{components:n})):r.createElement(m,a({ref:t},d))}));function m(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var o=n.length,a=new Array(o);a[0]=f;var s={};for(var c in t)hasOwnProperty.call(t,c)&&(s[c]=t[c]);s.originalType=e,s[p]="string"==typeof e?e:i,a[1]=s;for(var l=2;l<o;l++)a[l]=n[l];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}f.displayName="MDXCreateElement"},5034:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>c,contentTitle:()=>a,default:()=>u,frontMatter:()=>o,metadata:()=>s,toc:()=>l});var r=n(3262),i=(n(79),n(8570));const o={title:"IDeviceActionsListener",sidebar_position:2},a=void 0,s={unversionedId:"actions/interfaces/listeners/device/device-actions-listener",id:"actions/interfaces/listeners/device/device-actions-listener",title:"IDeviceActionsListener",description:"Implementation",source:"@site/docs/api/actions/interfaces/listeners/device/device-actions-listener.md",sourceDirName:"actions/interfaces/listeners/device",slug:"/actions/interfaces/listeners/device/device-actions-listener",permalink:"/boyka-framework/api/actions/interfaces/listeners/device/device-actions-listener",draft:!1,editUrl:"https://github.com/BoykaFramework/boyka-framework/edit/main/website/docs/api/actions/interfaces/listeners/device/device-actions-listener.md",tags:[],version:"current",lastUpdatedBy:"Wasiq Bhamla",lastUpdatedAt:1696686479,formattedLastUpdatedAt:"Oct 7, 2023",sidebarPosition:2,frontMatter:{title:"IDeviceActionsListener",sidebar_position:2},sidebar:"api",previous:{title:"IAndroidDeviceActionsListener",permalink:"/boyka-framework/api/actions/interfaces/listeners/device/android-device-actions-listener"},next:{title:"IAlertActionsListener",permalink:"/boyka-framework/api/actions/interfaces/listeners/drivers/alert-actions-listener"}},c={},l=[{value:"Implementation",id:"implementation",level:2},{value:"<code>onHideKeyboard ()</code>",id:"on-hide-keyboard",level:2},{value:"<code>onIsKeyboardVisible ()</code>",id:"on-is-keyboard-visible",level:2}],d={toc:l},p="wrapper";function u(e){let{components:t,...n}=e;return(0,i.kt)(p,(0,r.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h2",{id:"implementation"},"Implementation"),(0,i.kt)("p",null,"In order inject your customized logging for your reports or you want to perform any other action when the request gets executed, you can implement this class and provide your implementations for different methods available in this listener interface."),(0,i.kt)("h2",{id:"on-hide-keyboard"},(0,i.kt)("inlineCode",{parentName:"h2"},"onHideKeyboard ()")),(0,i.kt)("p",null,"This method will get executed before hiding the keyboard on Mobile."),(0,i.kt)("h2",{id:"on-is-keyboard-visible"},(0,i.kt)("inlineCode",{parentName:"h2"},"onIsKeyboardVisible ()")),(0,i.kt)("p",null,"This method will get executed before checking if the keyboard is visible on Mobile."))}u.isMDXComponent=!0}}]);
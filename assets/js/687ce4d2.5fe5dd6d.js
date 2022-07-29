"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[1804],{167:function(e,t,n){n.d(t,{Zo:function(){return d},kt:function(){return m}});var o=n(5721);function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,o)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){r(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function c(e,t){if(null==e)return{};var n,o,r=function(e,t){if(null==e)return{};var n,o,r={},i=Object.keys(e);for(o=0;o<i.length;o++)n=i[o],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(o=0;o<i.length;o++)n=i[o],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var l=o.createContext({}),s=function(e){var t=o.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},d=function(e){var t=s(e.components);return o.createElement(l.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return o.createElement(o.Fragment,{},t)}},p=o.forwardRef((function(e,t){var n=e.components,r=e.mdxType,i=e.originalType,l=e.parentName,d=c(e,["components","mdxType","originalType","parentName"]),p=s(n),m=r,k=p["".concat(l,".").concat(m)]||p[m]||u[m]||i;return n?o.createElement(k,a(a({ref:t},d),{},{components:n})):o.createElement(k,a({ref:t},d))}));function m(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=n.length,a=new Array(i);a[0]=p;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c.mdxType="string"==typeof e?e:r,a[1]=c;for(var s=2;s<i;s++)a[s]=n[s];return o.createElement.apply(null,a)}return o.createElement.apply(null,n)}p.displayName="MDXCreateElement"},2096:function(e,t,n){n.r(t),n.d(t,{assets:function(){return d},contentTitle:function(){return l},default:function(){return m},frontMatter:function(){return c},metadata:function(){return s},toc:function(){return u}});var o=n(744),r=n(4690),i=(n(5721),n(167)),a=["components"],c={title:"MouseActions",sidebar_position:4},l=void 0,s={unversionedId:"actions/mouse-actions",id:"actions/mouse-actions",title:"MouseActions",description:"clickOn",source:"@site/docs/api/actions/mouse-actions.md",sourceDirName:"actions",slug:"/actions/mouse-actions",permalink:"/boyka-framework/api/actions/mouse-actions",draft:!1,editUrl:"https://github.com/WasiqBhamla/boyka-framework/edit/main/website/docs/api/actions/mouse-actions.md",tags:[],version:"current",lastUpdatedBy:"Wasiq Bhamla",lastUpdatedAt:1659085203,formattedLastUpdatedAt:"Jul 29, 2022",sidebarPosition:4,frontMatter:{title:"MouseActions",sidebar_position:4},sidebar:"api",previous:{title:"KeyboardActions",permalink:"/boyka-framework/api/actions/keyboard-actions"},next:{title:"VerifyDriverActions",permalink:"/boyka-framework/api/actions/verify-driver-actions"}},d={},u=[{value:"<code>clickOn</code>",id:"clickon",level:2},{value:"<code>doubleClickOn</code>",id:"doubleclickon",level:2},{value:"<code>rightClickOn</code>",id:"rightclickon",level:2},{value:"<code>clickAndHold</code>",id:"clickandhold",level:2},{value:"<code>dragAndDropOn</code>",id:"draganddropon",level:2},{value:"<code>hoverOn</code>",id:"hoveron",level:2}],p={toc:u};function m(e){var t=e.components,n=(0,r.Z)(e,a);return(0,i.kt)("wrapper",(0,o.Z)({},p,n,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h2",{id:"clickon"},(0,i.kt)("inlineCode",{parentName:"h2"},"clickOn")),(0,i.kt)("p",null,"This method is used to click on the given element."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;\n. . .\nclickOn (locator);\n")),(0,i.kt)("h2",{id:"doubleclickon"},(0,i.kt)("inlineCode",{parentName:"h2"},"doubleClickOn")),(0,i.kt)("p",null,"This method is used to double click on the given element."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"import static com.github.wasiqb.boyka.actions.MouseActions.doubleClickOn;\n. . .\ndoubleClickOn (locator);\n")),(0,i.kt)("h2",{id:"rightclickon"},(0,i.kt)("inlineCode",{parentName:"h2"},"rightClickOn")),(0,i.kt)("p",null,"This method is used to right click on the given element."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"import static com.github.wasiqb.boyka.actions.MouseActions.rightClickOn;\n. . .\nrightClickOn (locator);\n")),(0,i.kt)("h2",{id:"clickandhold"},(0,i.kt)("inlineCode",{parentName:"h2"},"clickAndHold")),(0,i.kt)("p",null,"This method is used to click and hold on the given element."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"import static com.github.wasiqb.boyka.actions.MouseActions.clickAndHold;\n. . .\nclickAndHold (locator);\n")),(0,i.kt)("h2",{id:"draganddropon"},(0,i.kt)("inlineCode",{parentName:"h2"},"dragAndDropOn")),(0,i.kt)("p",null,"This method is used to drag and drop on the given element."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"import static com.github.wasiqb.boyka.actions.MouseActions.dragAndDropOn;\n. . .\ndragAndDropOn (locator);\n")),(0,i.kt)("h2",{id:"hoveron"},(0,i.kt)("inlineCode",{parentName:"h2"},"hoverOn")),(0,i.kt)("p",null,"This method is used to hover on the given element."),(0,i.kt)("pre",null,(0,i.kt)("code",{parentName:"pre",className:"language-java"},"import static com.github.wasiqb.boyka.actions.MouseActions.hoverOn;\n. . .\nhoverOn (locator);\n")))}m.isMDXComponent=!0}}]);
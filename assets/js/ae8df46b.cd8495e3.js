"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[3617],{167:function(e,t,n){n.d(t,{Zo:function(){return c},kt:function(){return f}});var r=n(5721);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){a(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function d(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var l=r.createContext({}),s=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):i(i({},t),e)),n},c=function(e){var t=s(e.components);return r.createElement(l.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},p=r.forwardRef((function(e,t){var n=e.components,a=e.mdxType,o=e.originalType,l=e.parentName,c=d(e,["components","mdxType","originalType","parentName"]),p=s(n),f=a,m=p["".concat(l,".").concat(f)]||p[f]||u[f]||o;return n?r.createElement(m,i(i({ref:t},c),{},{components:n})):r.createElement(m,i({ref:t},c))}));function f(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=n.length,i=new Array(o);i[0]=p;var d={};for(var l in t)hasOwnProperty.call(t,l)&&(d[l]=t[l]);d.originalType=e,d.mdxType="string"==typeof e?e:a,i[1]=d;for(var s=2;s<o;s++)i[s]=n[s];return r.createElement.apply(null,i)}return r.createElement.apply(null,n)}p.displayName="MDXCreateElement"},4415:function(e,t,n){n.r(t),n.d(t,{assets:function(){return c},contentTitle:function(){return l},default:function(){return f},frontMatter:function(){return d},metadata:function(){return s},toc:function(){return u}});var r=n(744),a=n(4690),o=(n(5721),n(167)),i=["components"],d={sidebar_position:3,title:"\ud83d\udc68\u200d\ud83e\uddbc Usage"},l=void 0,s={unversionedId:"getting-started/usage",id:"getting-started/usage",title:"\ud83d\udc68\u200d\ud83e\uddbc Usage",description:"Add as Maven dependency",source:"@site/docs/framework-docs/getting-started/usage.md",sourceDirName:"getting-started",slug:"/getting-started/usage",permalink:"/boyka-framework/docs/getting-started/usage",draft:!1,editUrl:"https://github.com/WasiqBhamla/boyka-framework/edit/main/website/docs/framework-docs/getting-started/usage.md",tags:[],version:"current",lastUpdatedBy:"Wasiq Bhamla",lastUpdatedAt:1659085203,formattedLastUpdatedAt:"Jul 29, 2022",sidebarPosition:3,frontMatter:{sidebar_position:3,title:"\ud83d\udc68\u200d\ud83e\uddbc Usage"},sidebar:"docs",previous:{title:"\ud83d\udea9 Pre-Requisite",permalink:"/boyka-framework/docs/getting-started/pre-requisite"},next:{title:"\ud83d\udd29 Configuration",permalink:"/boyka-framework/docs/guides/configuration"}},c={},u=[{value:"Add as Maven dependency",id:"add-as-maven-dependency",level:2},{value:"Add as Gradle dependency",id:"add-as-gradle-dependency",level:2},{value:"Download the jar",id:"download-the-jar",level:2}],p={toc:u};function f(e){var t=e.components,n=(0,a.Z)(e,i);return(0,o.kt)("wrapper",(0,r.Z)({},p,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("h2",{id:"add-as-maven-dependency"},"Add as Maven dependency"),(0,o.kt)("p",null,"You can start by adding the following dependency to your ",(0,o.kt)("inlineCode",{parentName:"p"},"pom.xml")," file:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-xml",metastring:"title=pom.xml",title:"pom.xml"},"<dependency>\n  <groupId>com.github.wasiqb.boyka</groupId>\n  <artifactId>boyka-framework</artifactId>\n  <version>0.7.0</version>\n</dependency>\n")),(0,o.kt)("h2",{id:"add-as-gradle-dependency"},"Add as Gradle dependency"),(0,o.kt)("p",null,"You can start by adding the following dependency to your ",(0,o.kt)("inlineCode",{parentName:"p"},"build.gradle")," file:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-groovy",metastring:"title=build.gradle",title:"build.gradle"},'compile "com.github.wasiqb.boyka:boyka-framework:0.7.0"\n')),(0,o.kt)("h2",{id:"download-the-jar"},"Download the jar"),(0,o.kt)("p",null,"You can also download the JAR files from the ",(0,o.kt)("a",{parentName:"p",href:"https://github.com/WasiqBhamla/boyka-framework/releases/latest"},"GitHub Releases page"),"."),(0,o.kt)("p",null,"After downloading the jar, you can add it to your class path."))}f.isMDXComponent=!0}}]);
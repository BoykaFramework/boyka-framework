"use strict";(self.webpackChunkwebsite=self.webpackChunkwebsite||[]).push([[3237],{2943:(e,t,r)=>{r.r(t),r.d(t,{default:()=>T});var a=r(79),i=r(4725),o=r(3262),s=r(3230);const n="features_ez_S",l="featureSvg_bdXF";var m=r(9419);const c=e=>{let{title:t,image:r}=e;return a.createElement("div",{className:"text--center"},a.createElement("img",{className:l,alt:t,src:(0,m.Z)(r)}))},d=e=>{let{title:t,description:r}=e;return a.createElement("div",{className:"text--center padding-horiz--md"},a.createElement("h2",null,t),a.createElement("p",null,r))},g=e=>{let{title:t,image:r,description:i}=e;return a.createElement("div",{className:(0,s.Z)("col col--4")},a.createElement(c,{title:t,image:r}),a.createElement(d,{title:t,description:i}))},u=e=>{let{features:t}=e;return a.createElement("section",{className:n},a.createElement("div",{className:"container"},a.createElement("div",{className:"row"},t.map(((e,t)=>a.createElement(g,(0,o.Z)({key:t},e)))))))},p=JSON.parse('{"BH":"/img/boyka-banner.png","TN":"","WL":"Ultimate Test Automation framework for Web, API, Android and iOS automation testing","ZY":[{"id":1,"text":"Get Started","type":"button--primary","href":"/docs/intro","target":"_self"},{"id":2,"text":"View on GitHub","type":"button--info","href":"https://github.com/BoykaFramework/boyka-framework","target":"_self"},{"id":3,"text":"Join us on Discord","type":"button--info","href":"https://dub.sh/boyka-discord","target":"_blank"}],"tr":[{"id":1,"userId":"BoykaFramework","repoName":"boyka-framework","type":"Star"},{"id":2,"userId":"BoykaFramework","repoName":"boyka-framework","type":"Watch"},{"id":3,"userId":"BoykaFramework","repoName":"boyka-framework","type":"Fork"},{"id":4,"userId":"BoykaFramework"}],"R2":[{"title":"Zero boilerplate code","image":"img/home/no-code.svg","description":"Exposes ready to use static methods to perform various actions on web, mobile and API platforms, thus reducing the need to write any boilerplate codes"},{"title":"Configurable","image":"img/home/configurable.svg","description":"Highly configurable via JSON files and environment variables. It is easy to extend and customize the framework"},{"title":"API Testing","image":"img/home/api-testing.svg","description":"Supports API testing with simple and easy to write test cases"},{"title":"Web Testing","image":"img/home/web-testing.svg","description":"Supports Web testing with Chrome, Firefox, Edge and Safari browsers"},{"title":"Mobile Testing","image":"img/home/mobile_devices.svg","description":"Supports Mobile testing with Android and iOS platforms for Native, Hybrid and Web applications"},{"title":"Cloud Support","image":"img/home/cloud-support.svg","description":"Provides facility to run tests on Cloud platforms like BrowserStack"},{"title":"Parallel execution","image":"img/home/parallel.svg","description":"Allows parallel and sequential execution of tests using any testing framework"},{"title":"Multi-Platform Testing","image":"img/home/real_time.svg","description":"Test multi-user multi-platform applications"},{"title":"Logging events","image":"img/home/logging.svg","description":"Provides logging support using Log4J2 to log all events occurred during test execution"},{"title":"Inbuilt verification","image":"img/home/inline-check.svg","description":"Provides inbuilt verification for inline assertion of element properties and API responses"},{"title":"Response schema verification","image":"img/home/schema-validation.svg","description":"Provides API response schema verification for Rest APIs"}]}'),h="HeroContainer_v2Am",b="HeroContent_wUuq",f="HeroTitle_d7d0",k="HeroDescription_Bq3r",v="CallToActions_tDGG",w="ctaButtons_Nl6G",y="SocialButtons_sdl9";var E=r(3120);const N=e=>{let{userId:t,repoName:r,type:i="Follow"}=e,o=`${t}`,s=`${i}`,n=`${i}`;return"Follow"!==i&&r&&(o+=`/${r}`,s+=` ${o}`),"Watch"===i?o+="/subscription":"Fork"===i?o+="/fork":"Follow"===i&&(n+=` @${t}`),a.createElement(E.Z,{href:`https://github.com/${o}`,"data-color-scheme":"no-preference: dark_dimmed; light: dark_dimmed; dark: dark_dimmed;","data-size":"large","data-show-count":"true","aria-label":`${s} on GitHub`},n)};var _=r(7691);const I=e=>{let{href:t,type:r,target:i,text:o}=e;return a.createElement(_.Z,{className:(0,s.Z)("button",r),to:(0,m.Z)(t),target:i},o)};var B=r(5623),S=r.n(B);const x=e=>{let{title:t,tagLine:r}=e;return a.createElement("div",{className:b},a.createElement("h1",{className:f},t),a.createElement(S(),{avgTypingDelay:40,cursor:{hideWhenDone:!0,show:!1}},a.createElement("p",{className:k},r)),a.createElement("a",{href:"https://www.producthunt.com/posts/boyka-framework?utm_source=badge-featured&utm_medium=badge&utm_souce=badge-boyka-framework",target:"_blank",rel:"noreferrer"},a.createElement("img",{src:"https://api.producthunt.com/widgets/embed-image/v1/featured.svg?post_id=352770&theme=light",alt:"Boyka Framework",width:"245",height:"45"})))},A=e=>{let{buttons:t}=e;return a.createElement("div",{className:w},t?.map((e=>a.createElement(I,{key:e.id,href:e.href,text:e.text,type:e.type,target:e.target}))))},F=e=>{let{gitButtons:t}=e;return a.createElement("div",{className:y},t?.map((e=>a.createElement(N,{key:e.id,id:e.id,userId:e.userId,repoName:e.repoName,type:e.type}))))},P=e=>{let{title:t,tagLine:r,image:i,buttons:o=[],gitButtons:s=[]}=e;return a.createElement("section",{className:h,style:{backgroundImage:`linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(${(0,m.Z)(i)})`}},a.createElement(x,{title:t,tagLine:r}),a.createElement("div",{className:v},a.createElement(A,{buttons:o}),a.createElement(F,{gitButtons:s})))},T=()=>a.createElement(i.Z,{title:p.TN,description:p.WL},a.createElement("main",null,a.createElement(P,{title:p.TN,tagLine:p.WL,image:p.BH,buttons:p.ZY,gitButtons:p.tr}),a.createElement(u,{features:p.R2})))}}]);
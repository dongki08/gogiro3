"use strict";(self.webpackChunkteam1_project=self.webpackChunkteam1_project||[]).push([[2114],{2245:(e,t,r)=>{r.d(t,{A:()=>a});var s=r(8095),n=r(255),c=r(579);const a=()=>(0,c.jsx)("div",{style:{position:"relative",width:"80px",height:"80px",background:"rgba(255,255,255,0.8)",display:"flex",flexDirection:"column",gap:"20px",justifyContent:"center",alignItems:"center",borderRadius:"5px",boxShadow:" 4px 4px 4px 0px rgba(0, 0, 0, 0.25)"},children:(0,c.jsx)(s.A,{color:n.J.g200,loading:!0})})},6345:(e,t,r)=>{r.d(t,{A:()=>c});var s=r(5043),n=r(579);const c=e=>{let{src:t,alt:r,placeholder:c,...a}=e;const[i,o]=(0,s.useState)(!1);return(0,n.jsxs)(n.Fragment,{children:[!i&&(0,n.jsx)("div",{style:{width:"100%",height:"100%"},children:c}),(0,n.jsx)("img",{src:t,alt:r,style:{display:i?"block":"none"},onLoad:()=>o(!0),...a})]})}},2114:(e,t,r)=>{r.r(t),r.d(t,{default:()=>b});var s=r(5043),n=r(3400),c=r(6957),a=r(5670),i=r(255),o=r(4008),l=r(1719),d=r(7475),p=r(579);const x=e=>{let{totalItems:t,itemPerPage:r}=e;const{moveToList:n,page:c}=(0,a.A)(),[o,l]=(0,s.useState)(0),[x,h]=(0,s.useState)(1),[u,m]=(0,s.useState)([]),[j,g]=(0,s.useState)(10),[v,f]=(0,s.useState)(0);(0,s.useEffect)((()=>{const e=Math.ceil(t/r);l(e);const s=Array.from({length:e},((e,t)=>t+1));m(s);const n=parseInt(new URLSearchParams(window.location.search).get("page"),10);!n||isNaN(n)?h(1):h(n)}),[t,r,x,c]);const b=u.slice(v,j);return(0,p.jsxs)(d.V3,{children:[x>1&&(0,p.jsx)(d.G,{onClick:()=>{h(x-1),x>10&&(x-1)%10===0&&(g(j-10),f(v-10)),n({page:x-1})},children:"\uc774\uc804"}),b.map((e=>(0,p.jsx)(d.G,{onClick:()=>{h(e),n({page:e})},style:x===e?{color:i.J.g900,fontWeight:"bold"}:{},children:e},e))),x<o&&(0,p.jsx)(d.G,{onClick:()=>{h(x+1),x>=10&&x%10===0&&(g(j+10),f(v+10)),n({page:x+1})},children:"\ub2e4\uc74c"})]})};var h=r(2245),u=r(6345);const m=()=>(0,p.jsxs)(d.UJ,{marginTop:"30px",background:i.J.g200,borderTop:"1px solid #8F8F8F",children:[(0,p.jsx)(d.F0,{children:"\ubc88\ud638"}),(0,p.jsx)(d.m7,{justifyContent:"center",children:"\uc81c\ubaa9"}),(0,p.jsxs)(d.un,{children:[(0,p.jsx)("div",{children:"\uc791\uc131\uc790"}),(0,p.jsx)("div",{children:"\uc791\uc131\uc77c"}),(0,p.jsx)("div",{children:"\uc88b\uc544\uc694"})]})]}),j=c.i,g=[{iboard:0,boardNum:0,iuser:0,writerName:"",writerPic:"",title:"",contents:"",createdAt:"",pics:[],count:0}],v=()=>{const{page:e,search:t,moveToRead:r,moveToAdd:c,moveToSearch:v}=(0,a.A)(),[f,b]=(0,s.useState)(g),[y,w]=(0,s.useState)({}),[A,S]=(0,s.useState)({iboard:0,boardNum:0,iuser:0,writerName:"",writerPic:"",title:"",contents:"",createdAt:"",pics:[],count:0}),[k,P]=(0,s.useState)(!1);(0,s.useEffect)((()=>{const r={page:e,search:t};(0,n.vS)({param:r,successFn:O,failFn:C,errorFn:E})}),[e,t]);const O=e=>{b(e),P(!1)},C=e=>{P(!1),console.log(e)},E=e=>{P(!1),console.log(e)},[F,N]=(0,s.useState)("");return(0,p.jsxs)(d.qd,{children:[k?(0,p.jsx)(l.A,{}):null,(0,p.jsx)(m,{}),f.map((e=>(0,p.jsxs)("div",{children:[(0,p.jsxs)(d._D,{selected:y===e.iboard,onClick:()=>{(e=>{y===e.iboard?w(null):(w(e.iboard),A.iboard!=e.iboard&&S(e))})(e)},children:[(0,p.jsx)(d.F0,{color:i.J.g700,children:1===e.announce?(0,p.jsx)("div",{children:(0,p.jsx)("img",{src:"".concat("","/assets/images/notice.svg")})}):(0,p.jsx)("div",{children:e.boardNum})}),(0,p.jsx)(d.m7,{children:e.title}),(0,p.jsxs)(d.un,{color:i.J.g700,children:[(0,p.jsx)("div",{children:e.writerName}),(0,p.jsx)("div",{children:e.createdAt}),(0,p.jsx)("div",{children:e.totalFav})]})]}),y===e.iboard&&(0,p.jsxs)(d.kX,{children:[(0,p.jsx)(d.vP,{children:(0,p.jsx)(d.g7,{children:A.pics.map(((e,t)=>e&&(0,p.jsx)("div",{className:"thumbnail",children:(0,p.jsx)(u.A,{src:"".concat(j,"/pic/community/").concat(A.iboard,"/").concat(e),alt:"img_".concat(t+1),placeholder:(0,p.jsx)("div",{children:(0,p.jsx)(h.A,{})})})},t)))})}),(0,p.jsx)(d.pD,{children:(0,p.jsxs)(d.l$,{children:[(0,p.jsxs)(d.R3,{children:[A.writerPic?(0,p.jsx)(u.A,{src:"".concat(j,"/pic/user/").concat(A.iuser,"/").concat(A.writerPic),alt:"\ud504\ub85c\ud544\uc0ac\uc9c4",placeholder:(0,p.jsx)("div",{children:(0,p.jsx)("img",{src:"".concat("","/assets/images/user_profile.png")})})}):(0,p.jsx)(u.A,{src:"".concat("","/assets/images/user_profile.png"),alt:"\ud504\ub85c\ud544 \uae30\ubcf8\uc0ac\uc9c4",placeholder:(0,p.jsx)("div",{children:(0,p.jsx)("img",{src:"".concat("","/assets/images/user_profile.png")})})}),(0,p.jsx)(d.PK,{children:(0,p.jsx)("div",{children:A.writerName})})]}),(0,p.jsx)(d.m4,{children:A.contents})]})}),(0,p.jsx)(d.MJ,{children:(0,p.jsx)("div",{onClick:()=>{r(A.iboard)},children:(0,p.jsx)(o.A,{bttext:"\uae00 \ub354\ubcf4\uae30"})})})]})]},e.iboard))),(0,p.jsx)(x,{totalItems:f[0].boardAllCount,itemPerPage:10}),(0,p.jsx)(d.Gn,{children:(0,p.jsx)("div",{className:"searchBox",children:(0,p.jsx)("form",{onSubmit:e=>{e.preventDefault(),v({page:1,search:F}),b(g)},children:(0,p.jsxs)("div",{children:[(0,p.jsx)("input",{type:"text",placeholder:"\uae00 \uc81c\ubaa9\uc744 \uac80\uc0c9\ud574\ubcf4\uc138\uc694",value:F,onChange:e=>{N(e.target.value)}}),(0,p.jsx)("button",{className:"icon",style:{border:"none",background:"none"},children:(0,p.jsx)("img",{src:"/assets/images/search.svg",alt:"search"})})]})})})}),(0,p.jsx)(d.cH,{children:(0,p.jsx)("div",{onClick:c,children:(0,p.jsx)(o.A,{bttext:"\uae00\uc4f0\uae30"})})})]})};var f=r(947);const b=()=>(0,p.jsxs)("div",{children:[(0,p.jsx)(f.A,{timg:"".concat("","/assets/images/community_header_1.png"),tname:"\uace0\uae30\uc7a1\ub2f4",tcontent:'"\uc778\uc0dd\uc740 \uace0\uae30\uc11c \uace0\uae30\ub2e4"'}),(0,p.jsx)(v,{})]})},8095:(e,t,r)=>{r.d(t,{A:()=>l});var s=r(5043),n=r(4524),c=r(7628),a=function(){return a=Object.assign||function(e){for(var t,r=1,s=arguments.length;r<s;r++)for(var n in t=arguments[r])Object.prototype.hasOwnProperty.call(t,n)&&(e[n]=t[n]);return e},a.apply(this,arguments)},i=function(e,t){var r={};for(var s in e)Object.prototype.hasOwnProperty.call(e,s)&&t.indexOf(s)<0&&(r[s]=e[s]);if(null!=e&&"function"===typeof Object.getOwnPropertySymbols){var n=0;for(s=Object.getOwnPropertySymbols(e);n<s.length;n++)t.indexOf(s[n])<0&&Object.prototype.propertyIsEnumerable.call(e,s[n])&&(r[s[n]]=e[s[n]])}return r},o=(0,c.J)("FadeLoader","50% {opacity: 0.3} 100% {opacity: 1}","fade");const l=function(e){var t=e.loading,r=void 0===t||t,c=e.color,l=void 0===c?"#000000":c,d=e.speedMultiplier,p=void 0===d?1:d,x=e.cssOverride,h=void 0===x?{}:x,u=e.height,m=void 0===u?15:u,j=e.width,g=void 0===j?5:j,v=e.radius,f=void 0===v?2:v,b=e.margin,y=void 0===b?2:b,w=i(e,["loading","color","speedMultiplier","cssOverride","height","width","radius","margin"]),A=(0,n.L)(y).value+18,S=A/2+A/5.5,k=a({display:"inherit",position:"relative",fontSize:"0",top:A,left:A,width:"".concat(3*A,"px"),height:"".concat(3*A,"px")},h),P=function(e){return{position:"absolute",width:(0,n.p)(g),height:(0,n.p)(m),margin:(0,n.p)(y),backgroundColor:l,borderRadius:(0,n.p)(f),transition:"2s",animationFillMode:"both",animation:"".concat(o," ").concat(1.2/p,"s ").concat(.12*e,"s infinite ease-in-out")}},O=a(a({},P(1)),{top:"".concat(A,"px"),left:"0"}),C=a(a({},P(2)),{top:"".concat(S,"px"),left:"".concat(S,"px"),transform:"rotate(-45deg)"}),E=a(a({},P(3)),{top:"0",left:"".concat(A,"px"),transform:"rotate(90deg)"}),F=a(a({},P(4)),{top:"".concat(-1*S,"px"),left:"".concat(S,"px"),transform:"rotate(45deg)"}),N=a(a({},P(5)),{top:"".concat(-1*A,"px"),left:"0"}),_=a(a({},P(6)),{top:"".concat(-1*S,"px"),left:"".concat(-1*S,"px"),transform:"rotate(-45deg)"}),J=a(a({},P(7)),{top:"0",left:"".concat(-1*A,"px"),transform:"rotate(90deg)"}),T=a(a({},P(8)),{top:"".concat(S,"px"),left:"".concat(-1*S,"px"),transform:"rotate(45deg)"});return r?s.createElement("span",a({style:k},w),s.createElement("span",{style:O}),s.createElement("span",{style:C}),s.createElement("span",{style:E}),s.createElement("span",{style:F}),s.createElement("span",{style:N}),s.createElement("span",{style:_}),s.createElement("span",{style:J}),s.createElement("span",{style:T})):null}}}]);
//# sourceMappingURL=2114.1f5c4812.chunk.js.map
"use strict";(self.webpackChunkteam1_project=self.webpackChunkteam1_project||[]).push([[8377],{8377:(e,n,t)=>{t.r(n),t.d(n,{default:()=>z});var i=t(5043),c=t(831),s=t(6957),l=t(5940);const a="".concat(s.i,"/api/owner/menu"),o={checkShop:0,imenu:0,ishop:0,ibutcher:0,price:0,menu:"",pic:""},r=(0,c.eU)({key:"atomMenuInfoState",default:o}),d=(0,c.eU)({key:"menuRefreshState",default:1});var p=t(1051),u=t(1719),h=t(8095),x=t(255),m=t(579);const g=()=>(0,m.jsx)("div",{style:{position:"relative",width:"160px",height:"160px",background:"rgba(255,255,255,0.8)",display:"flex",flexDirection:"column",gap:"20px",justifyContent:"center",alignItems:"center",borderRadius:"5px",boxShadow:" 4px 4px 4px 0px rgba(0, 0, 0, 0.25)"},children:(0,m.jsx)(h.A,{color:x.J.g200,loading:!0})});var v=t(4949);const j=e=>{let{src:n,alt:t,placeholder:c,...s}=e;const[l,a]=(0,i.useState)(!1);return(0,m.jsxs)(m.Fragment,{children:[!l&&(0,m.jsx)("div",{style:{width:"100%",height:"100%"},children:c}),(0,m.jsx)("img",{src:n,alt:t,style:{width:"160px",height:"160px",cursor:"pointer",borderRadius:"4px",display:l?"block":"none"},onLoad:()=>a(!0),...s})]})};var f,y,w=t(9396),b=t(7528),N=t(5903);const k=N.A.div(f||(f=(0,b.A)(["\n  display: flex;\n  flex-wrap: wrap;\n  gap: 10px; // \ud56d\ubaa9 \uc0ac\uc774\uc758 \uac04\uaca9\n  justify-content: flex-start;\n"]))),A=N.A.div(y||(y=(0,b.A)(["\n  position: relative;\n  display: flex;\n  align-items: flex-start;\n  gap: 10px;\n  .menu-img {\n    width: 160px;\n    height: 160px;\n    background: url(<path-to-image>), lightgray 50% / cover no-repeat, #f3f4f6;\n  }\n  .menu-info {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 5px;\n    width: 160px;\n    height: 60px;\n    position: absolute;\n    right: 0px;\n    top: 100px;\n    opacity: 0.5;\n    background: var(--gray-scale-1000, #000);\n    color: #fff;\n    padding: 10px;\n    /* 12/regular */\n    font-family: Pretendard;\n    font-size: 12px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 150%; /* 18px */\n  }\n  .menu-title {\n    white-space: nowrap; /* \uc904\ubc14\uafc8 \uc5c6\uc774 \ud55c \uc904\ub85c \ud45c\uc2dc */\n    overflow: hidden; /* \ub0b4\uc6a9\uc774 \ub118\uce60 \uacbd\uc6b0 \uc228\uae40 \ucc98\ub9ac */\n    text-overflow: ellipsis; /* \ub118\uce5c \ud14d\uc2a4\ud2b8\ub97c \ub9d0\uc904\uc784\ud45c\ub85c \ud45c\uc2dc */\n    width: 100%; /* \ubd80\ubaa8 \ucee8\ud14c\uc774\ub108\uc758 \ud3ed\uc5d0 \ub9de\ucda4 */\n  }\n"])));var C=t(22);const S=s.i,I=()=>{const{isModal:e,openModal:n,closeModal:t}=(0,w.A)(),s=(0,c.vc)(r),o=(0,c.vc)(p.w),h=(0,c.vc)(d),[x,f]=(0,c.L4)(r),[y,b]=(0,i.useState)([]),[N,I]=(0,i.useState)(!1);(0,i.useEffect)((()=>{(async()=>{I(!0);try{const e=await(async e=>{let{ishop:n}=e;try{const e={headers:{"Content-type":"application/json"}};return(await l.A.get("".concat(a),e)).data}catch(t){return void console.log(t)}})({ishop:s.ishop});if(!e)return void n("\uba54\ub274 \uc815\ubcf4","\uac00\uc838\uc624\ub294\ub370 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",t);b(e)}catch(e){return void n("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",t)}finally{I(!1)}console.log("\ub4f1\ub85d\ub41c \uba54\ub274\ub9ac\uc2a4\ud2b8",y)})()}),[h]);return(0,m.jsxs)(C.G$,{children:[e.isOpen&&(0,m.jsx)(v.A,{title:e.title,content:e.content,callFn:e.callFn}),N?(0,m.jsx)(u.A,{}):null,(0,m.jsxs)(C.cl,{children:[(0,m.jsx)("div",{className:"big-title",children:"\uba54\ub274\ubaa9\ub85d"}),(0,m.jsx)("div",{className:"title"}),(0,m.jsx)(k,{children:y.map(((e,n)=>(0,m.jsxs)(A,{onClick:()=>{(e=>{f(e),console.log(s.imenu)})(e)},style:{cursor:"pointer"},children:[(0,m.jsx)("div",{className:"menu-img",children:(0,m.jsx)(j,{src:1!==o.checkShop?null!==e&&void 0!==e&&e.pic?"".concat(S,"/pic/shop/").concat(e.ishop,"/menu/").concat(e.pic):"".concat("","/assets/images/menuImg.png"):null!==e&&void 0!==e&&e.pic?"".concat(S,"/pic/butcher/").concat(e.ishop,"/menu/").concat(e.pic):"".concat("","/assets/images/menuImg.png"),alt:"\ubbf8\ub9ac\ubcf4\uae30".concat(n),placeholder:(0,m.jsx)("div",{children:(0,m.jsx)(g,{})})})}),(0,m.jsxs)("div",{className:"menu-info",children:[(0,m.jsx)("div",{className:"menu-title",children:e.menu}),(0,m.jsxs)("div",{children:[new Intl.NumberFormat("ko-KR").format(e.price)," \uc6d0"]})]})]},n)))})]})]})};var F=t(2076),M=t(3734),J=t(1966),R=t(1375);const B=s.i,L=()=>{var e;const{isModal:n,openModal:t,closeModal:s}=(0,w.A)(),[h,x]=(0,i.useState)(!1),[g,j]=(0,c.L4)(r),f=(0,c.vc)(p.w),[y,b]=(0,c.L4)(d),[N,k]=(0,i.useState)(null),A=(0,i.useRef)(null),[S,I]=(0,i.useState)("default"),[L,z]=(0,i.useState)("default");return(0,m.jsxs)(C.G$,{children:[n.isOpen&&(0,m.jsx)(v.A,{title:n.title,content:n.content,callFn:n.callFn}),h?(0,m.jsx)(u.A,{}):null,(0,m.jsxs)(C.cl,{children:[(0,m.jsx)("div",{className:"big-title",children:"\uba54\ub274 \ub4f1\ub85d / \uc218\uc815\ud558\uae30"}),(0,m.jsx)("div",{className:"text-guide",children:"\uba54\ub274\ubaa9\ub85d\uc5d0\uc11c \uba54\ub274\ub97c \ud074\ub9ad\ud558\uc2dc\uba74 \ud574\ub2f9 \uba54\ub274\ub97c \uc218\uc815\ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4"}),(0,m.jsxs)("div",{className:"title",children:[(0,m.jsx)("div",{children:"\uba54\ub274\uc0ac\uc9c4"}),(0,m.jsx)("div",{className:"essential",children:"*"})]}),(0,m.jsxs)("div",{className:"pics-container",children:[(0,m.jsx)("div",{className:"text-guide",children:"5MB \uc774\ud558 1\uc7a5\ub9cc \ub4f1\ub85d \uac00\ub2a5\ud569\ub2c8\ub2e4"}),(0,m.jsx)(J.J,{type:"button",onClick:()=>{var e;null===(e=A.current)||void 0===e||e.click()},children:"\uc0ac\uc9c4\ub4f1\ub85d"}),(0,m.jsx)("input",{type:"file",ref:A,style:{display:"none"},onChange:e=>{if(e.target.files&&e.target.files[0]){const n=e.target.files[0];if(n.size>5242880)return void t("\uc0ac\uc9c4 \ub4f1\ub85d","5MB \uc774\ud558\ub9cc \uac00\ub2a5\ud569\ub2c8\ub2e4.",s);const i=URL.createObjectURL(n);j((e=>({...e,pic:i}))),k(n)}}}),(0,m.jsx)("div",{className:"pics-thumb",children:g.pic&&(0,m.jsx)(M.A,{src:g.pic.startsWith("blob")?g.pic:1!==f.checkShop?"".concat(B,"/pic/shop/").concat(g.ishop,"/menu/").concat(g.pic):"".concat(B,"/pic/butcher/").concat(g.ishop,"/menu/").concat(g.pic),alt:"\ubbf8\ub9ac\ubcf4\uae30 \uc774\ubbf8\uc9c0",placeholder:(0,m.jsx)("div",{children:(0,m.jsx)(F.A,{})})})})]})]}),(0,m.jsxs)(C.cl,{children:[(0,m.jsxs)("div",{className:"title",children:[(0,m.jsx)("div",{children:"\uba54\ub274\uba85"}),(0,m.jsx)("div",{className:"essential",children:"*"})]}),(0,m.jsx)(R.Ey,{state:S,children:(0,m.jsx)(R.fz,{type:"text",placeholder:"\uba54\ub274\uba85\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694",value:g.menu||"",onChange:e=>{const n=e.target.value;j({...g,menu:n&&n})},onFocus:()=>I("focus"),onBlur:()=>{const e=g.menu;0===e.length||e.length>30?I("error"):I(e?"filled":"default")}})}),(0,m.jsxs)("div",{className:"name-guide",children:[(0,m.jsx)("div",{className:"text-guide",children:"\uc22b\uc790, \ud55c\uae00, \uc601\ubb38, \ud2b9\uc218\ubb38\uc790 \uc0ac\uc6a9\uac00\ub2a5"}),(0,m.jsxs)("div",{className:"text-length",children:[null===(e=g.menu)||void 0===e?void 0:e.length,"/30"]})]}),(0,m.jsxs)("div",{className:"title",children:[(0,m.jsx)("div",{children:"\uba54\ub274\uac00\uaca9"}),(0,m.jsx)("div",{className:"essential",children:"*"})]}),(0,m.jsx)(R.Ey,{state:L,children:(0,m.jsx)(R.fz,{type:"number",placeholder:"\uba54\ub274 \uac00\uaca9\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694",value:g.price||"",onChange:e=>{const n=e.target.value;j({...g,price:n?parseInt(n,10):0})},onFocus:()=>z("focus"),onBlur:()=>{const e=g.price;z(e<=0||0==e?"error":e?"filled":"default")}})}),(0,m.jsx)("div",{className:"text-guide",children:"\uc22b\uc790\ub9cc \uc0ac\uc6a9\uac00\ub2a5, \ub2e8\uc704: \uc6d0"})]}),(0,m.jsxs)(C.I_,{children:[(0,m.jsx)(J.J,{type:"button",onClick:async()=>{x(!0);const e=new FormData;N&&e.append("pic",N);const n=new Blob([JSON.stringify({menu:g.menu,price:g.price})],{type:"application/json"});e.append("dto",n);try{const n=await(async e=>{let{menuInfo:n}=e;try{const e={headers:{"Content-Type":"multipart/form-data"}};return(await l.A.post("".concat(a),n,e)).data}catch(t){return void console.log(t)}})({menuInfo:e});return n?(console.log("\uba54\ub274 \uc815\ubcf4 \uc218\uc815 \uc131\uacf5"),console.log("\uba54\ub274 \uc815\ubcf4",g),t("\uba54\ub274 \uc815\ubcf4","\uba54\ub274\uac00 \ub4f1\ub85d \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",s),b(-1*y),void j(o)):(console.log("\uba54\ub274\uc815\ubcf4",g),void t("\uba54\ub274 \uc815\ubcf4","\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",s))}catch(i){return console.log("\uba54\ub274 \uc815\ubcf4 \ub4f1\ub85d \uc548\ub428"),void t("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",s)}finally{x(!1)}},children:"\ub4f1\ub85d\ud558\uae30"}),(0,m.jsx)(J.J,{type:"button",onClick:async()=>{if(!g.imenu)return void t("\uba54\ub274 \uc218\uc815","\uc218\uc815\ud560 \uba54\ub274\ub97c \uc120\ud0dd\ud574\uc8fc\uc138\uc694",s);x(!0);const e=new FormData;N&&e.append("pic",N);const n=new Blob([JSON.stringify({imenu:g.imenu,menu:g.menu,price:g.price})],{type:"application/json"});e.append("dto",n);try{const n=await(async e=>{let{menuInfo:n}=e;try{const e={headers:{"Content-Type":"multipart/form-data"}};return(await l.A.put("".concat(a),n,e)).data}catch(t){return void console.log(t)}})({menuInfo:e});return n?(console.log("\uba54\ub274 \uc815\ubcf4 \uc218\uc815 \uc131\uacf5"),console.log("\uba54\ub274 \uc815\ubcf4",g),t("\uba54\ub274 \uc815\ubcf4","\uba54\ub274\uac00 \ub4f1\ub85d \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",s),b(-1*y),void j(o)):(console.log("\uba54\ub274\uc815\ubcf4",g),void t("\uba54\ub274 \uc815\ubcf4","\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",s))}catch(i){return console.log("\uba54\ub274 \uc815\ubcf4 \ub4f1\ub85d \uc548\ub428"),void t("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",s)}finally{x(!1)}},children:"\uc218\uc815\ud558\uae30"}),(0,m.jsx)(J.J,{type:"button",onClick:async()=>{if(g.imenu){x(!0);try{return await(async e=>{let{imenu:n}=e;try{const e={headers:{"Content-Type":"application/json"}};return(await l.A.delete("".concat(a,"?imenu=").concat(n),e)).data}catch(t){return void console.log(t)}})({imenu:g.imenu})?(t("\uba54\ub274 \uc0ad\uc81c","\uba54\ub274\uac00 \uc0ad\uc81c\ub418\uc5c8\uc2b5\ub2c8\ub2e4",s),b(-1*y),void j(o)):(t("\uba54\ub274 \uc0ad\uc81c","\uba54\ub274 \uc0ad\uc81c\ub97c \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",s),void console.log("imenu",g.imenu))}catch(e){return void t("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",s)}finally{x(!1)}}else t("\uba54\ub274 \uc0ad\uc81c","\uc0ad\uc81c\ud560 \uba54\ub274\ub97c \uc120\ud0dd\ud574\uc8fc\uc138\uc694",s)},children:"\uc0ad\uc81c\ud558\uae30"}),(0,m.jsx)(J.J,{type:"button",onClick:()=>{j(o)},children:"\ucd08\uae30\ud654"})]})]})},z=()=>(0,m.jsxs)(C.iw,{children:[(0,m.jsx)(C.mX,{children:(0,m.jsx)("div",{className:"page-title",children:"\uba54\ub274 \uad00\ub9ac"})}),(0,m.jsxs)(C.NU,{children:[(0,m.jsx)(C.V2,{children:(0,m.jsx)(I,{})}),(0,m.jsx)(C.Pz,{children:(0,m.jsx)(L,{})})]})]})}}]);
//# sourceMappingURL=8377.bb7e3871.chunk.js.map
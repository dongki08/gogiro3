"use strict";(self.webpackChunkteam1_project=self.webpackChunkteam1_project||[]).push([[8377],{8377:(e,n,t)=>{t.r(n),t.d(n,{default:()=>L});var i=t(5043),s=t(831),l=t(6957),c=t(5940);const a="".concat(l.i,"/api/owner/menu"),o={checkShop:0,imenu:0,ishop:0,price:0,menu:"",pic:""},r=(0,s.eU)({key:"atomMenuInfoState",default:o}),d=(0,s.eU)({key:"menuRefreshState",default:1});var p=t(1719),u=t(4949),h=t(579);const x=e=>{let{src:n,alt:t,placeholder:s,...l}=e;const[c,a]=(0,i.useState)(!1);return(0,h.jsxs)(h.Fragment,{children:[!c&&(0,h.jsx)("div",{style:{width:"100%",height:"100%"},children:s}),(0,h.jsx)("img",{src:n,alt:t,style:{width:"160px",height:"160px",cursor:"pointer",borderRadius:"4px",display:c?"block":"none"},onLoad:()=>a(!0),...l})]})};var m,j,g=t(9396),f=t(7528),v=t(5903);const y=v.A.div(m||(m=(0,f.A)(["\n  display: flex;\n  flex-wrap: wrap;\n  gap: 10px; // \ud56d\ubaa9 \uc0ac\uc774\uc758 \uac04\uaca9\n  justify-content: flex-start;\n"]))),w=v.A.div(j||(j=(0,f.A)(["\n  position: relative;\n  display: flex;\n  align-items: flex-start;\n  gap: 10px;\n  .menu-img {\n    width: 160px;\n    height: 160px;\n    background: url(<path-to-image>), lightgray 50% / cover no-repeat, #f3f4f6;\n  }\n  .menu-info {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 5px;\n    width: 160px;\n    height: 60px;\n    position: absolute;\n    right: 0px;\n    top: 100px;\n    opacity: 0.5;\n    background: var(--gray-scale-1000, #000);\n    color: #fff;\n    padding: 10px;\n    /* 12/regular */\n    font-family: Pretendard;\n    font-size: 12px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 150%; /* 18px */\n  }\n  .menu-title {\n    white-space: nowrap; /* \uc904\ubc14\uafc8 \uc5c6\uc774 \ud55c \uc904\ub85c \ud45c\uc2dc */\n    overflow: hidden; /* \ub0b4\uc6a9\uc774 \ub118\uce60 \uacbd\uc6b0 \uc228\uae40 \ucc98\ub9ac */\n    text-overflow: ellipsis; /* \ub118\uce5c \ud14d\uc2a4\ud2b8\ub97c \ub9d0\uc904\uc784\ud45c\ub85c \ud45c\uc2dc */\n    width: 100%; /* \ubd80\ubaa8 \ucee8\ud14c\uc774\ub108\uc758 \ud3ed\uc5d0 \ub9de\ucda4 */\n  }\n"])));var b=t(22),N=t(8095),k=t(255);const A=()=>(0,h.jsx)("div",{style:{position:"relative",width:"160px",height:"160px",background:"rgba(255,255,255,0.8)",display:"flex",flexDirection:"column",gap:"20px",justifyContent:"center",alignItems:"center",borderRadius:"5px",boxShadow:" 4px 4px 4px 0px rgba(0, 0, 0, 0.25)"},children:(0,h.jsx)(N.A,{color:k.J.g200,loading:!0})}),C=l.i,S=()=>{const{isModal:e,openModal:n,closeModal:t}=(0,g.A)(),l=(0,s.vc)(r),o=(0,s.vc)(d),[m,j]=(0,s.L4)(r),[f,v]=(0,i.useState)([]),[N,k]=(0,i.useState)(!1);(0,i.useEffect)((()=>{(async()=>{k(!0);try{const e=await(async e=>{let{ishop:n}=e;try{const e={headers:{"Content-type":"application/json"}};return(await c.A.get("".concat(a),e)).data}catch(t){return void console.log(t)}})({ishop:l.ishop});if(!e)return void n("\uba54\ub274 \uc815\ubcf4","\uac00\uc838\uc624\ub294\ub370 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",t);v(e)}catch(e){return void n("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",t)}finally{k(!1)}console.log("\ub4f1\ub85d\ub41c \uba54\ub274\ub9ac\uc2a4\ud2b8",f)})()}),[o]);return(0,h.jsxs)(b.G$,{children:[e.isOpen&&(0,h.jsx)(u.A,{title:e.title,content:e.content,callFn:e.callFn}),N?(0,h.jsx)(p.A,{}):null,(0,h.jsxs)(b.cl,{children:[(0,h.jsx)("div",{className:"big-title",children:"\uba54\ub274\ubaa9\ub85d"}),(0,h.jsx)("div",{className:"title"}),(0,h.jsx)(y,{children:f.map(((e,n)=>(0,h.jsxs)(w,{onClick:()=>{(e=>{j(e),console.log(l.imenu)})(e)},style:{cursor:"pointer"},children:[(0,h.jsx)("div",{className:"menu-img",children:(0,h.jsx)(x,{src:e.pic?"".concat(C,"/pic/shop/").concat(e.ishop,"/menu/").concat(e.pic):"".concat("","/assets/images/menuImg.png"),alt:"\ubbf8\ub9ac\ubcf4\uae30".concat(n),placeholder:(0,h.jsx)("div",{children:(0,h.jsx)(A,{})})})}),(0,h.jsxs)("div",{className:"menu-info",children:[(0,h.jsx)("div",{className:"menu-title",children:e.menu}),(0,h.jsxs)("div",{children:[new Intl.NumberFormat("ko-KR").format(e.price)," \uc6d0"]})]})]},n)))})]})]})};var F=t(1966),I=t(1375),M=t(3734),J=t(2076);const R=l.i,B=()=>{var e;const{isModal:n,openModal:t,closeModal:l}=(0,g.A)(),[x,m]=(0,i.useState)(!1),[j,f]=(0,s.L4)(r),[v,y]=(0,s.L4)(d),[w,N]=(0,i.useState)(null),k=(0,i.useRef)(null),[A,C]=(0,i.useState)("default"),[S,B]=(0,i.useState)("default");return(0,h.jsxs)(b.G$,{children:[n.isOpen&&(0,h.jsx)(u.A,{title:n.title,content:n.content,callFn:n.callFn}),x?(0,h.jsx)(p.A,{}):null,(0,h.jsxs)(b.cl,{children:[(0,h.jsx)("div",{className:"big-title",children:"\uba54\ub274 \ub4f1\ub85d / \uc218\uc815\ud558\uae30"}),(0,h.jsx)("div",{className:"text-guide",children:"\uba54\ub274\ubaa9\ub85d\uc5d0\uc11c \uba54\ub274\ub97c \ud074\ub9ad\ud558\uc2dc\uba74 \ud574\ub2f9 \uba54\ub274\ub97c \uc218\uc815\ud560 \uc218 \uc788\uc2b5\ub2c8\ub2e4"}),(0,h.jsxs)("div",{className:"title",children:[(0,h.jsx)("div",{children:"\uba54\ub274\uc0ac\uc9c4"}),(0,h.jsx)("div",{className:"essential",children:"*"})]}),(0,h.jsxs)("div",{className:"pics-container",children:[(0,h.jsx)("div",{className:"text-guide",children:"5MB \uc774\ud558 1\uc7a5\ub9cc \ub4f1\ub85d \uac00\ub2a5\ud569\ub2c8\ub2e4"}),(0,h.jsx)(F.J,{type:"button",onClick:()=>{var e;null===(e=k.current)||void 0===e||e.click()},children:"\uc0ac\uc9c4\ub4f1\ub85d"}),(0,h.jsx)("input",{type:"file",ref:k,style:{display:"none"},onChange:e=>{if(e.target.files&&e.target.files[0]){const n=e.target.files[0];if(n.size>5242880)return void t("\uc0ac\uc9c4 \ub4f1\ub85d","5MB \uc774\ud558\ub9cc \uac00\ub2a5\ud569\ub2c8\ub2e4.",l);const i=URL.createObjectURL(n);f((e=>({...e,pic:i}))),N(n)}}}),(0,h.jsx)("div",{className:"pics-thumb",children:j.pic&&(0,h.jsx)(M.A,{src:j.pic.startsWith("blob")?j.pic:"".concat(R,"/pic/shop/").concat(j.ishop,"/menu/").concat(j.pic),alt:"\ubbf8\ub9ac\ubcf4\uae30 \uc774\ubbf8\uc9c0",placeholder:(0,h.jsx)("div",{children:(0,h.jsx)(J.A,{})})})})]})]}),(0,h.jsxs)(b.cl,{children:[(0,h.jsxs)("div",{className:"title",children:[(0,h.jsx)("div",{children:"\uba54\ub274\uba85"}),(0,h.jsx)("div",{className:"essential",children:"*"})]}),(0,h.jsx)(I.Ey,{state:A,children:(0,h.jsx)(I.fz,{type:"text",placeholder:"\uba54\ub274\uba85\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694",value:j.menu||"",onChange:e=>{const n=e.target.value;f({...j,menu:n&&n})},onFocus:()=>C("focus"),onBlur:()=>{const e=j.menu;0===e.length||e.length>30?C("error"):C(e?"filled":"default")}})}),(0,h.jsxs)("div",{className:"name-guide",children:[(0,h.jsx)("div",{className:"text-guide",children:"\uc22b\uc790, \ud55c\uae00, \uc601\ubb38, \ud2b9\uc218\ubb38\uc790 \uc0ac\uc6a9\uac00\ub2a5"}),(0,h.jsxs)("div",{className:"text-length",children:[null===(e=j.menu)||void 0===e?void 0:e.length,"/30"]})]}),(0,h.jsxs)("div",{className:"title",children:[(0,h.jsx)("div",{children:"\uba54\ub274\uac00\uaca9"}),(0,h.jsx)("div",{className:"essential",children:"*"})]}),(0,h.jsx)(I.Ey,{state:S,children:(0,h.jsx)(I.fz,{type:"number",placeholder:"\uba54\ub274 \uac00\uaca9\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694",value:j.price||"",onChange:e=>{const n=e.target.value;f({...j,price:n?parseInt(n,10):0})},onFocus:()=>B("focus"),onBlur:()=>{const e=j.price;B(e<=0||0==e?"error":e?"filled":"default")}})}),(0,h.jsx)("div",{className:"text-guide",children:"\uc22b\uc790\ub9cc \uc0ac\uc6a9\uac00\ub2a5, \ub2e8\uc704: \uc6d0"})]}),(0,h.jsxs)(b.I_,{children:[(0,h.jsx)(F.J,{type:"button",onClick:async()=>{m(!0);const e=new FormData;w&&e.append("pic",w);const n=new Blob([JSON.stringify({menu:j.menu,price:j.price})],{type:"application/json"});e.append("dto",n);try{const n=await(async e=>{let{menuInfo:n}=e;try{const e={headers:{"Content-Type":"multipart/form-data"}};return(await c.A.post("".concat(a),n,e)).data}catch(t){return void console.log(t)}})({menuInfo:e});return n?(console.log("\uba54\ub274 \uc815\ubcf4 \uc218\uc815 \uc131\uacf5"),console.log("\uba54\ub274 \uc815\ubcf4",j),t("\uba54\ub274 \uc815\ubcf4","\uba54\ub274\uac00 \ub4f1\ub85d \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",l),y(-1*v),void f(o)):(console.log("\uba54\ub274\uc815\ubcf4",j),void t("\uba54\ub274 \uc815\ubcf4","\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",l))}catch(i){return console.log("\uba54\ub274 \uc815\ubcf4 \ub4f1\ub85d \uc548\ub428"),void t("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",l)}finally{m(!1)}},children:"\ub4f1\ub85d\ud558\uae30"}),(0,h.jsx)(F.J,{type:"button",onClick:async()=>{if(!j.imenu)return void t("\uba54\ub274 \uc218\uc815","\uc218\uc815\ud560 \uba54\ub274\ub97c \uc120\ud0dd\ud574\uc8fc\uc138\uc694",l);m(!0);const e=new FormData;w&&e.append("pic",w);const n=new Blob([JSON.stringify({imenu:j.imenu,menu:j.menu,price:j.price})],{type:"application/json"});e.append("dto",n);try{const n=await(async e=>{let{menuInfo:n}=e;try{const e={headers:{"Content-Type":"multipart/form-data"}};return(await c.A.put("".concat(a),n,e)).data}catch(t){return void console.log(t)}})({menuInfo:e});return n?(console.log("\uba54\ub274 \uc815\ubcf4 \uc218\uc815 \uc131\uacf5"),console.log("\uba54\ub274 \uc815\ubcf4",j),t("\uba54\ub274 \uc815\ubcf4","\uba54\ub274\uac00 \ub4f1\ub85d \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",l),y(-1*v),void f(o)):(console.log("\uba54\ub274\uc815\ubcf4",j),void t("\uba54\ub274 \uc815\ubcf4","\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",l))}catch(i){return console.log("\uba54\ub274 \uc815\ubcf4 \ub4f1\ub85d \uc548\ub428"),void t("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",l)}finally{m(!1)}},children:"\uc218\uc815\ud558\uae30"}),(0,h.jsx)(F.J,{type:"button",onClick:async()=>{if(j.imenu){m(!0);try{return await(async e=>{let{imenu:n}=e;try{const e={headers:{"Content-Type":"application/json"}};return(await c.A.delete("".concat(a,"?imenu=").concat(n),e)).data}catch(t){return void console.log(t)}})({imenu:j.imenu})?(t("\uba54\ub274 \uc0ad\uc81c","\uba54\ub274\uac00 \uc0ad\uc81c\ub418\uc5c8\uc2b5\ub2c8\ub2e4",l),y(-1*v),void f(o)):(t("\uba54\ub274 \uc0ad\uc81c","\uba54\ub274 \uc0ad\uc81c\ub97c \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4",l),void console.log("imenu",j.imenu))}catch(e){return void t("\uc11c\ubc84 \uc624\ub958","\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud558\uc138\uc694",l)}finally{m(!1)}}else t("\uba54\ub274 \uc0ad\uc81c","\uc0ad\uc81c\ud560 \uba54\ub274\ub97c \uc120\ud0dd\ud574\uc8fc\uc138\uc694",l)},children:"\uc0ad\uc81c\ud558\uae30"}),(0,h.jsx)(F.J,{type:"button",onClick:()=>{f(o)},children:"\ucd08\uae30\ud654"})]})]})},L=()=>(0,h.jsxs)(b.iw,{children:[(0,h.jsx)(b.mX,{children:(0,h.jsx)("div",{className:"page-title",children:"\uba54\ub274 \uad00\ub9ac"})}),(0,h.jsxs)(b.NU,{children:[(0,h.jsx)(b.V2,{children:(0,h.jsx)(S,{})}),(0,h.jsx)(b.Pz,{children:(0,h.jsx)(B,{})})]})]})}}]);
//# sourceMappingURL=8377.bf6dcc44.chunk.js.map
"use strict";(self.webpackChunkteam1_project=self.webpackChunkteam1_project||[]).push([[1634],{6957:(n,e,t)=>{t.d(e,{i:()=>i});const i=""},2657:(n,e,t)=>{t.d(e,{D6:()=>d,HZ:()=>p,PX:()=>s,Sq:()=>c,TX:()=>o,fE:()=>x,ih:()=>r});var i=t(7154),a=t(5940);const l="".concat("","/api"),o=async n=>{let{isLogin:e,ishop:t}=n;try{const n={headers:{"Content-Type":"application/json"}},o=e?a.A:i.A,s=await o.get("".concat(l,"/shop/").concat(t),n);if("2"===s.status.toString().charAt(0))return console.log("\ubaa9\ub85d \ud638\ucd9c \uc131\uacf5"),s.data;console.log("\ubaa9\ub85d \ud638\ucd9c \uc624\ub958")}catch(o){console.log(o)}},s=async n=>{let{params:e}=n;console.log("\ud30c\ub77c\ubbf8\ud130",e);try{const n=await i.A.get("".concat(l,"/shop"),{params:e});if("2"===n.status.toString().charAt(0))return console.log("\ubaa9\ub85d \ud638\ucd9c \uc131\uacf5"),n.data;console.log("\ubaa9\ub85d \ud638\ucd9c \uc624\ub958")}catch(t){console.log(t)}},r=async n=>{let{reviewData:e}=n;try{const n={headers:{"Content-Type":"multipart/form-data"}};return(await a.A.post("".concat(l,"/review"),e,n)).data}catch(t){throw console.log(t),t}},p=async n=>{let{reserData:e}=n;try{const n={headers:{"Content-Type":"application/json"}};return(await a.A.post("".concat(l,"/reservation"),e,n)).data}catch(t){throw console.log(t),t}},d=async n=>{let{dataForm:e}=n;console.log("\ub370\uc774\ud130\ud3fc",e);try{return(await i.A.post("".concat(l,"/status"),e)).data}catch(t){throw console.log(""),t}},x=async n=>{let{replyData:e}=n;console.log("axios data : ",e);try{const n={headers:{"Content-Type":"application/json"}};return(await a.A.put("".concat("","/api/owner/review"),e,n)).data}catch(t){throw console.log("error"),t}},c=async n=>{let{reportData:e}=n;console.log("axios",e);try{const n={headers:{"Content-Type":"application/json"}};return(await a.A.post("".concat("","/api/user/review/report"),e,n)).data}catch(t){throw console.log(t),t}}},22:(n,e,t)=>{t.d(e,{G$:()=>w,I_:()=>A,NU:()=>u,Pz:()=>y,V2:()=>v,cl:()=>j,iw:()=>g,mX:()=>m});var i,a,l,o,s,r,p,d,x,c=t(7528),h=t(5903),f=t(255);const g=h.A.form(i||(i=(0,c.A)(["\n  position: relative;\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 17px;\n  font-family: Pretendard;\n"]))),m=h.A.div(a||(a=(0,c.A)(["\n  position: fixed;\n  height: 68px;\n  top: 114px;\n  left: 210px;\n  display: flex;\n  width: calc(100% - 210px);\n  padding: 11px 36px;\n  justify-content: space-between;\n  align-items: center;\n  background: #fff;\n  z-index: 1;\n  /* Shadow */\n  box-shadow: 0px 1px 2px -1px rgba(0, 0, 0, 0.1),\n    0px 1px 3px 0px rgba(0, 0, 0, 0.1);\n\n  .page-title {\n    display: flex;\n    width: 136px;\n    justify-content: space-between;\n    align-items: center;\n    flex-shrink: 0;\n    color: var(--grayscale-90, #1c1c1e);\n    font-size: 24px;\n    font-style: normal;\n    font-weight: 700;\n    line-height: normal;\n  }\n"]))),u=h.A.div(l||(l=(0,c.A)(["\n  display: flex;\n  align-items: flex-start;\n  padding-left: 20px;\n  // topNavBar \uace0\uc815 \uc2dc \uc801\uc6a9\n  margin-top: 85px;\n  margin-bottom: 50px;\n  margin-left: 210px;\n  /* z-index: -999; */\n  gap: 20px;\n"]))),y=h.A.div(o||(o=(0,c.A)(["\n  position: fixed;\n  left: 810px;\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 10px;\n  /* z-index: 998; */\n"]))),v=h.A.div(s||(s=(0,c.A)(["\n  position: relative;\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 10px;\n"]))),w=h.A.div(r||(r=(0,c.A)(["\n  position: relative;\n  display: flex;\n  width: 560px;\n  padding: 20px 30px;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 16px;\n  border-radius: 8px;\n  background: #fff;\n  /* shadow */\n  box-shadow: 0px 1px 2px -1px rgba(0, 0, 0, 0.1),\n    0px 1px 3px 0px rgba(0, 0, 0, 0.1);\n  /* z-index: 999; */\n"]))),A=h.A.div(p||(p=(0,c.A)(["\n  display: flex;\n  align-items: center;\n  gap: 20px;\n"]))),j=h.A.div(d||(d=(0,c.A)(["\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 8px;\n  form {\n    display: flex;\n    align-items: flex-start;\n    gap: 16px;\n  }\n  label {\n    display: flex;\n    align-items: center;\n    gap: 8px;\n    color: var(--gray-900, #111827);\n    font-family: Pretendard;\n    font-size: 14px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 22px; /* 157.143% */\n  }\n\n  .big-title {\n    display: flex;\n    align-items: flex-start;\n    gap: 2px;\n    color: var(--gray-900, #111827);\n    /* background-color: ","; */\n    /* 16/semibold */\n    font-family: Pretendard;\n    font-size: 24px;\n    font-style: normal;\n    font-weight: 600;\n    line-height: 150%; /* 24px */\n  }\n  .title {\n    display: flex;\n    align-items: flex-start;\n    gap: 2px;\n    color: var(--gray-900, #111827);\n    /* 16/semibold */\n    font-family: Pretendard;\n    font-size: 16px;\n    font-style: normal;\n    font-weight: 600;\n    line-height: 150%; /* 24px */\n  }\n  .essential {\n    color: var(--red-500, #ef4444);\n    /* 16/semibold */\n    font-family: Pretendard;\n    font-size: 16px;\n    font-style: normal;\n    font-weight: 600;\n    line-height: 150%; /* 24px */\n  }\n  .text-guide {\n    display: flex;\n    align-items: flex-start;\n    color: var(--gray-500, #6b7280);\n    /* 12/regular */\n    font-family: Pretendard;\n    font-size: 12px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 150%; /* 18px */\n  }\n  .text-length {\n    display: flex;\n    justify-content: flex-end;\n    align-items: flex-start;\n    flex: 1 0 0;\n    color: var(--gray-500, #6b7280);\n    text-align: right;\n    /* 12/regular */\n    font-family: Pretendard;\n    font-size: 12px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 150%; /* 18px */\n  }\n  .pics-container {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 16px;\n  }\n  .pics-thumb {\n    display: flex;\n    align-items: center;\n    gap: 10px;\n  }\n  .name-guide {\n    display: flex;\n    width: 500px;\n    justify-content: space-between;\n    align-items: flex-start;\n    align-self: stretch;\n  }\n  .tel {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 8px;\n    align-self: stretch;\n  }\n  .open {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 8px;\n    align-self: stretch;\n  }\n  .location-box {\n    display: flex;\n    align-items: flex-start;\n    gap: 6px;\n  }\n  .location-input-box {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 8px;\n  }\n  .menu-pics {\n    display: flex;\n    width: 500px;\n    align-items: flex-start;\n    align-content: flex-start;\n    gap: 10px;\n    flex-wrap: wrap;\n  }\n  .check-box-wrap {\n    display: flex;\n    width: 500px;\n    flex-wrap: wrap;\n    align-items: flex-start;\n    gap: 16px;\n  }\n  .radio-wrap {\n    display: flex;\n    align-items: flex-start;\n    gap: 16px;\n  }\n\n  .preview-img {\n    width: 500px;\n    height: 340px;\n    object-fit: cover;\n    /* overflow: hidden; */\n  }\n  .shop-info-box {\n    display: flex;\n    width: 500px;\n    height: 109px;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 10px;\n    position: absolute;\n    bottom: 20px;\n    background: rgba(17, 17, 17, 0.35);\n  }\n  .shop-info {\n    display: flex;\n    height: 109px;\n    padding: 10px 30px;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 5px;\n    flex-shrink: 0;\n  }\n  .shop-name {\n    width: 100%;\n    height: 18px;\n    flex-shrink: 0;\n    color: #fff;\n    font-family: DAEAM_LEE_TAE_JOON;\n    font-size: 16px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 20px */\n  }\n  .shop-info-detail-box {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 5px;\n  }\n  .shop-info-text-wrap {\n    display: flex;\n    align-items: flex-start;\n  }\n  .shop-info-cate {\n    width: 45px;\n    color: var(--gray-scale-100, #f5f5f5);\n    font-family: Pretendard;\n    font-size: 10px;\n    font-style: normal;\n    font-weight: 700;\n    line-height: 125%; /* 12.5px */\n  }\n  .shop-info-detail {\n    color: var(--gray-scale-100, #f5f5f5);\n    font-family: Pretendard;\n    font-size: 10px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 12.5px */\n  }\n"])),f.J.g200);(0,h.A)(j)(x||(x=(0,c.A)(["\n  display: flex;\n  flex-direction: row;\n  width: 500px;\n  justify-content: space-between;\n  align-items: flex-start;\n  flex-shrink: 0;\n  .menu-add-pic {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 8px;\n  }\n  .btn-wrap {\n    display: flex;\n    justify-content: flex-end;\n    align-items: flex-start;\n    gap: 10px;\n  }\n"])))},7430:(n,e,t)=>{t.d(e,{A:()=>s});var i,a=t(7528),l=(t(5043),t(5903)),o=t(579);const s=n=>{let{star:e}=n;const t=new Array(5).fill("/assets/images/review_n_count.png"),s=l.A.div(i||(i=(0,a.A)(["\n    display: flex;\n    gap: 2px;\n    /* img {\n      width: 14px;\n      height: 14px;\n    } */\n    .star {\n      width: 14px;\n      height: 14px;\n    }\n  "])));for(let i=0;i<e;i++)t[i]="/assets/images/review_count.png";return(0,o.jsx)(s,{children:t.map(((n,e)=>(0,o.jsx)("img",{className:"star",src:n,alt:"star"},e)))})}},1918:(n,e,t)=>{t.d(e,{A:()=>l});var i=t(5043),a=t(579);const l=n=>{let{src:e,alt:t,placeholder:l,width:o,height:s,...r}=n;const[p,d]=(0,i.useState)(!1);return(0,a.jsxs)(a.Fragment,{children:[!p&&(0,a.jsx)("div",{style:{width:"100%",height:"100%"},children:l}),(0,a.jsx)("img",{src:e,alt:t,style:{width:"".concat(o,"px"),height:"".concat(s,"px"),cursor:"pointer",borderRadius:"4px",display:p?"block":"none"},onLoad:()=>d(!0),...r})]})}},2865:(n,e,t)=>{t.d(e,{A:()=>o});var i=t(8095),a=t(255),l=t(579);const o=n=>{let{width:e,height:t}=n;const o={position:"relative",width:"".concat(e,"px"),height:"".concat(t,"px"),background:"rgba(255,255,255,0.8)",display:"flex",flexDirection:"column",gap:"20px",justifyContent:"center",alignItems:"center",borderRadius:"5px",boxShadow:" 4px 4px 4px 0px rgba(0, 0, 0, 0.25)"};return(0,l.jsx)("div",{style:o,children:(0,l.jsx)(i.A,{color:a.J.g200,loading:!0})})}},7483:(n,e,t)=>{t.r(e),t.d(e,{default:()=>k});var i=t(3747),a=t(5043),l=(t(5692),t(3647),t(4014),t(3874),t(162),t(1054)),o=t(22),s=t(2360),r=t(613),p=t(5980),d=t(5225),x=t(4975),c=t(7430),h=(t(781),t(115),t(7097)),f=t(6957),g=t(2657),m=t(2156),u=t(4949),y=t(1918),v=t(2865),w=t(579);const A=f.i,j=n=>{let{reviewData:e,updateRender:t}=n;const[i,l]=(0,a.useState)(!1),{adminState:o}=(0,m.A)();console.log("value",e);const[s,f]=(0,a.useState)(null),j=o.ishop,b=o.checkShop;console.log("ckshop",b);const k={ireview:0,checkShop:0,comment:""},{isModal:E,openModal:_,closeModal:z}=(0,r.A)(),S=(0,h.n)({mutationFn:n=>(0,g.fE)({replyData:n}),onSuccess:n=>{n&&_("\ub4f1\ub85d \uc131\uacf5","\ub2f5\uae00 \ub4f1\ub85d\uc744 \uc131\uacf5\ud558\uc600\uc2b5\ub2c8\ub2e4.",(()=>{z(),t(),O(k)}))},onError:()=>{}}),[D,O]=(0,a.useState)(k),[J,N]=(0,a.useState)(!1),T=null===e||void 0===e?void 0:e.updatedAt,P=new Date(T),C=P.getFullYear()+"-"+("0"+(P.getMonth()+1)).slice(-2)+"-"+("0"+P.getDate()).slice(-2)+" "+("0"+P.getHours()).slice(-2)+":"+("0"+P.getMinutes()).slice(-2),M=null===e||void 0===e?void 0:e.createdAt,F=new Date(M).getFullYear()+"-"+("0"+(P.getMonth()+1)).slice(-2)+"-"+("0"+P.getDate()).slice(-2)+" "+("0"+P.getHours()).slice(-2)+":"+("0"+P.getMinutes()).slice(-2);return(0,a.useEffect)((()=>{console.log("exist \ubcc0\uacbd \uac10\uc9c0"),console.log("refresh \ubcc0\uacbd \uac10\uc9c0",i)}),[null===e||void 0===e?void 0:e.exist,i]),(0,w.jsxs)(p.BD,{children:[E.isOpen&&(0,w.jsx)(u.A,{title:E.title,content:E.content,callFn:E.callFn}),(0,w.jsx)("div",{style:{width:"100%",display:"flex"},children:(0,w.jsx)("div",{children:(0,w.jsxs)(p.s4,{children:[(0,w.jsxs)(p.jh,{children:[(0,w.jsx)(p.O5,{children:(0,w.jsxs)(p.$5,{children:[(0,w.jsx)("div",{children:null===(null===e||void 0===e?void 0:e.writerPic)?(0,w.jsx)(y.A,{src:"/assets/images/favicon.png",alt:"",width:40,height:40,placeholder:(0,w.jsx)("div",{children:(0,w.jsx)(v.A,{width:40,height:40})})}):(0,w.jsx)(y.A,{src:"".concat(A,"/pic/user/").concat(e.iuser,"/").concat(e.writerPic),alt:"",width:40,height:40,placeholder:(0,w.jsx)("div",{children:(0,w.jsx)(v.A,{width:40,height:40})})})}),(0,w.jsxs)("div",{className:"nickname-star-wrap",children:[(0,w.jsxs)("div",{className:"user-date",children:[(0,w.jsx)("div",{children:(0,w.jsx)("span",{children:null===e||void 0===e?void 0:e.nickname})}),(0,w.jsx)(p.Hr,{children:(0,w.jsx)("span",{children:F})})]}),(0,w.jsx)("div",{children:(0,w.jsx)(c.A,{star:null===e||void 0===e?void 0:e.star})})]})]})}),(0,w.jsx)(p.ar,{children:(0,w.jsx)("span",{children:null===e||void 0===e?void 0:e.review})})]}),(0,w.jsxs)(p.f5,{children:[(0,w.jsx)(x.RC,{style:{"--swiper-navigation-color":"#fff","--swiper-pagination-color":"#fff"},loop:!0,spaceBetween:10,navigation:!0,thumbs:{swiper:s},modules:[d.U1,d.Vx,d.WO],className:"mySwiper2",children:null===e||void 0===e?void 0:e.pics.map(((n,t)=>(0,w.jsx)(x.qr,{children:0===b?(0,w.jsx)(y.A,{alt:"",width:300,height:180,placeholder:(0,w.jsx)("div",{children:(0,w.jsx)(v.A,{width:300,height:180})}),src:"".concat(A,"/pic/shop/").concat(j,"/review/").concat(e.ireview,"/").concat(n)}):(0,w.jsx)(y.A,{alt:"",width:300,height:180,placeholder:(0,w.jsx)("div",{children:(0,w.jsx)(v.A,{width:300,height:180})}),src:"".concat(A,"/pic/butcher/").concat(j,"/review/").concat(e.ireview,"/").concat(n)})},t)))}),(0,w.jsx)(x.RC,{onSwiper:f,loop:!0,spaceBetween:8,slidesPerView:5,freeMode:!0,watchSlidesProgress:!0,modules:[d.U1,d.Vx,d.WO],className:"mySwiper",children:null===e||void 0===e?void 0:e.pics.map(((n,t)=>(0,w.jsx)(x.qr,{children:0===b?(0,w.jsx)(y.A,{alt:"",width:54,height:54,placeholder:(0,w.jsx)("div",{children:(0,w.jsx)(v.A,{width:54,height:54})}),src:"".concat(A,"/pic/shop/").concat(j,"/review/").concat(e.ireview,"/").concat(n)}):(0,w.jsx)(y.A,{alt:"",width:54,height:54,placeholder:(0,w.jsx)("div",{children:(0,w.jsx)(v.A,{width:54,height:54})}),src:"".concat(A,"/pic/butcher/").concat(j,"/review/").concat(e.ireview,"/").concat(n)})},t)))})]})]})})}),(0,w.jsx)("div",{}),1===(null===e||void 0===e?void 0:e.exist)?(0,w.jsx)("div",{style:{marginBottom:"5px",marginTop:"15px"},children:(0,w.jsxs)(p.Ub,{children:[(0,w.jsxs)(p.Tk,{children:[(0,w.jsx)(p.Yh,{children:(0,w.jsx)("span",{children:"\uc0ac\uc7a5\ub2d8"})}),(0,w.jsx)(p.Fu,{children:(0,w.jsx)("span",{children:C})})]}),(0,w.jsx)(p.uv,{children:(0,w.jsx)("span",{children:null===e||void 0===e?void 0:e.comment})})]})}):(0,w.jsxs)("div",{style:{width:"100%"},children:[(0,w.jsx)("div",{style:{float:"right",marginBottom:"20px"},children:(0,w.jsx)(p.Gh,{onClick:J?()=>{N(!1)}:()=>{N(!0)},children:(0,w.jsx)("span",{children:J?"\uc791\uc131\ucde8\uc18c":"\ub2f5\uae00\uc791\uc131"})})}),J?(0,w.jsxs)("div",{children:[(0,w.jsx)("div",{children:(0,w.jsx)(p.N9,{placeholder:"\ub2f5\uae00\uc744 \ub0a8\uaca8\uc8fc\uc138\uc694.",name:"comment",value:D.comment,maxLength:30,onChange:n=>(n=>{O({...D,[n.target.name]:n.target.value}),console.log("change",D)})(n)})}),(0,w.jsx)(p.h6,{children:(0,w.jsx)(p.Gh,{onClick:()=>(async(n,e)=>{O((t=>({...t,ireview:n,checkShop:e}))),await S.mutate({...D,ireview:n,checkShop:e})})(null===e||void 0===e?void 0:e.ireview,null===e||void 0===e?void 0:e.checkShop),children:(0,w.jsx)("span",{children:"\uc791\uc131\uc644\ub8cc"})})})]}):null]})]})},b=[{checkShop:0,ireview:0,ishop:0,iuser:0,star:0,nickname:"",comment:"",exist:0,review:"",createdAt:"",updatedAt:"",pics:[""],writerPic:""}],k=()=>{const{page:n,size:e,moveToSize:t}=(0,r.A)(),[d,x]=(0,a.useState)(!1),c=()=>{x((n=>!n))},h={page:n,size:e},[f,g]=(0,a.useState)(!1),{data:m}=(0,i.I)({queryKey:["reviewData",h,d],queryFn:()=>(0,l.F1)({params:h})}),[u,y]=(0,a.useState)(null),v=m||b;console.log("\ub370\uc774\ud130",v);return(0,w.jsxs)(p.N_,{children:[(0,w.jsx)(o.mX,{children:(0,w.jsx)("div",{className:"page-title",children:"\ub9e4\uc7a5 \ub9ac\ubdf0 \uad00\ub9ac"})}),(0,w.jsx)("div",{style:{width:"100%",display:"flex",flexWrap:"wrap",justifyContent:"flex-start",gap:"30px"},children:null===v||void 0===v?void 0:v.map(((n,e)=>(0,w.jsx)("div",{style:{flex:"1 0 30%",maxWidth:"30%"},children:(0,w.jsx)(j,{reviewData:v[e],updateRender:c})},null===n||void 0===n?void 0:n.ireview)))}),(0,w.jsx)("div",{style:{display:"flex",width:"100%",justifyContent:"center",paddingBottom:"30px",paddingTop:"80px"},children:(0,w.jsx)(s.k,{onClick:()=>{t({size:e+3})},children:(0,w.jsx)("span",{children:"\ub354\ubcf4\uae30"})})})]})}},781:(n,e,t)=>{t.d(e,{Q8:()=>g,Ud:()=>m,c4:()=>h,dZ:()=>f,qS:()=>u});var i,a,l,o,s,r,p,d=t(7528),x=t(5903),c=t(255);const h=x.A.div(i||(i=(0,d.A)(["\n  position: relative;\n  margin-top: 114px;\n"]))),f=x.A.div(a||(a=(0,d.A)(["\n  position: relative;\n  display: flex;\n  align-items: flex-start;\n"]))),g=x.A.div(l||(l=(0,d.A)(["\n  position: fixed;\n  display: flex;\n  align-items: flex-start;\n  width: 210px;\n  height: 1530px;\n  padding: 16px;\n  gap: 10px;\n  background-color: #202734;\n  z-index: 996;\n"]))),m=x.A.div(o||(o=(0,d.A)(["\n  position: relative;\n  display: flex;\n  flex-direction: column;\n  button {\n    display: flex;\n    align-items: center;\n    font-family: Pretendard;\n    font-size: ",";\n    color: ",";\n    font-style: normal;\n    font-weight: 400;\n    line-height: 40px;\n    border: none;\n    background: none;\n    /* margin-bottom: 10px; */\n    cursor: pointer;\n  }\n"])),c.Z.strong,c.J.g600),u=x.A.div(s||(s=(0,d.A)(["\n  position: relative;\n  width: calc(100% - 210px);\n  /* background-color: pink; */\n"])));x.A.div(r||(r=(0,d.A)(["\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 10px;\n"]))),x.A.div(p||(p=(0,d.A)(["\n  display: flex;\n  img {\n    width: 20px;\n    height: 20px;\n  }\n"])))},5980:(n,e,t)=>{t.d(e,{$5:()=>F,BD:()=>M,Fu:()=>P,Gh:()=>C,Hr:()=>R,N9:()=>D,N_:()=>z,O5:()=>L,Tk:()=>J,Ub:()=>O,Yh:()=>T,ar:()=>B,f5:()=>H,h6:()=>S,jh:()=>q,s4:()=>U,uv:()=>N});var i,a,l,o,s,r,p,d,x,c,h,f,g,m,u,y,v,w,A,j,b=t(7528),k=t(5903),E=t(4764),_=t(255);const z=k.A.div(i||(i=(0,b.A)(["\n  width: 1080px;\n  padding-left: 20px;\n  margin-top: 85px;\n  margin-bottom: 50px;\n  margin-left: 210px;\n"]))),S=(k.A.div(a||(a=(0,b.A)(["\n  display: flex;\n"]))),k.A.div(l||(l=(0,b.A)(["\n  float: right;\n  padding: 20px 0px;\n"])))),D=(0,k.A)(E.A)(o||(o=(0,b.A)(['\n  display: flex;\n  width: 306px;\n  height: 44px;\n  padding: 10px 20px;\n  flex-direction: column;\n  justify-content: center;\n  align-items: center;\n  gap: 10px;\n  flex-shrink: 0;\n  overflow: hidden;\n  resize: none;\n\n  border-radius: 10px;\n  border: 1px solid var(--gray-scale-500, #8f8f8f);\n  background: #fff;\n\n  font-size: 14px;\n  font-family: "Pretendard";\n']))),O=k.A.div(s||(s=(0,b.A)(["\n  width: 306px;\n  /* height: 108px; */\n  flex-shrink: 0;\n  /* gap: 20px; */\n\n  border-radius: 5px;\n  border: 1px solid ",";\n  /* box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.5); */\n  background: ",";\n\n  display: flex;\n  /* width: 306px; */\n  padding: 15px;\n  /* gap: 10px; */\n  flex-direction: column;\n  gap: 20px;\n  /* justify-content: flex-end; */\n  /* align-items: flex-start; */\n"])),_.J.g200,_.J.g100),J=k.A.div(r||(r=(0,b.A)(["\n  width: 100%;\n  /* height: 44px; */\n  /* padding: 10px 20px; */\n  display: flex;\n  /* flex-direction: column; */\n  /* justify-content: center; */\n  align-items: center;\n  gap: 10px;\n  /* flex-shrink: 0; */\n"]))),N=k.A.div(p||(p=(0,b.A)(["\n  display: flex;\n  width: 259px;\n  /* height: 60px; */\n  flex-direction: column;\n  justify-content: center;\n  flex-shrink: 0;\n\n  span {\n    color: ",";\n    font-family: DAEAM_LEE_TAE_JOON;\n    font-size: 16px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 13.75px */\n  }\n"])),_.J.g1000),T=k.A.div(d||(d=(0,b.A)(["\n  span {\n    color: ",";\n    font-family: DAEAM_LEE_TAE_JOON;\n    font-size: 16px;\n    font-style: normal;\n    font-weight: 600;\n    line-height: 125%; /* 17.5px */\n  }\n"])),_.J.g1000),P=k.A.div(x||(x=(0,b.A)(["\n  span {\n    color: var(--gray-scale-500, #8f8f8f);\n    text-align: center;\n    font-family: DAEAM_LEE_TAE_JOON;\n    font-size: 14px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 13.75px */\n  }\n"]))),C=k.A.button(c||(c=(0,b.A)(["\n  cursor: pointer;\n  display: flex;\n  /* width: 64px; */\n  /* height: 35px; */\n  padding: 5px 10px;\n  flex-direction: column;\n  /* justify-content: center; */\n  /* align-items: center; */\n  gap: 10px;\n  flex-shrink: 0;\n\n  border-radius: 10px;\n  border: 2px solid var(--sub, #066e52);\n  background: #fff;\n  span {\n    color: var(--primary, #d60117);\n    text-align: center;\n    font-family: DAEAM_LEE_TAE_JOON;\n    font-size: 14px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 23.75px */\n  }\n  :hover {\n    background: ",";\n    color: ",";\n  }\n  :active {\n    background: ",";\n    color: ",";\n  }\n"])),_.J.g200,_.J.primary,_.J.secondary,_.J.grayScale),M=(k.A.div(h||(h=(0,b.A)(["\n  position: relative;\n  padding: 20px 35px;\n"]))),k.A.div(f||(f=(0,b.A)(["\n  float: right;\n  display: flex;\n  gap: 10px;\n"]))),k.A.div(g||(g=(0,b.A)(["\n  display: flex;\n  flex-direction: column;\n  /* justify-content: space-around; */\n  justify-content: center;\n  align-items: center;\n  padding: 10px;\n  /* gap: 30px; */\n  /* padding: 80px 0px; */\n  box-shadow: 0px 1px 2px -1px rgba(0, 0, 0, 0.1),\n    0px 1px 3px 0px rgba(0, 0, 0, 0.1);\n"])))),F=k.A.div(m||(m=(0,b.A)(['\n  display: flex;\n  padding-top: 10px;\n  width: 100%;\n  align-items: center;\n  gap: 12px;\n\n  img {\n    width: 40px;\n    height: 40px;\n    border-radius: 30px;\n  }\n  span {\n    font-size: 14px;\n    font-family: "DAEAM_LEE_TAE_JOON";\n  }\n  .user-date {\n    display: flex;\n    gap: 10px;\n  }\n  .nickname-star-wrap {\n    height: 40px;\n    display: flex;\n    padding-top: 3px;\n    flex-direction: column;\n    gap: 5px;\n  }\n']))),L=k.A.div(u||(u=(0,b.A)(["\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 5px;\n"]))),B=k.A.div(y||(y=(0,b.A)(['\n  width: 300px;\n  /* height: 40px; */\n  padding-bottom: 10px;\n  span {\n    color: #000;\n\n    /* Rugular 14 */\n    font-family: "DAEAM_LEE_TAE_JOON";\n    font-size: 18px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 17.5px */\n  }\n']))),R=k.A.div(v||(v=(0,b.A)(["\n  display: flex;\n  width: 100%;\n  /* height: 18px; */\n  flex-direction: column;\n  justify-content: center;\n\n  span {\n    color: ",';\n    /* Rugular 14 */\n    font-family: "DAEAM_LEE_TAE_JOON";\n    font-size: 12px;\n    font-style: normal;\n    font-weight: 400;\n    line-height: 125%; /* 17.5px */\n  }\n'])),_.J.g500),q=k.A.div(w||(w=(0,b.A)(["\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 30px;\n  /* margin: 10px; */\n"]))),U=k.A.div(A||(A=(0,b.A)(["\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  width: 100%;\n  /* gap: 20px; */\n  /* margin: 10px; */\n"]))),H=k.A.div(j||(j=(0,b.A)(["\n  margin-left: -1px;\n  /* img {\n    width: 320px;\n  } */\n  /* height: 3; */\n  /* color: transparent; */\n"])))},162:()=>{},115:()=>{}}]);
//# sourceMappingURL=1634.0c4d9649.chunk.js.map
"use strict";(self.webpackChunkteam1_project=self.webpackChunkteam1_project||[]).push([[7173],{4949:(e,t,n)=>{n.d(t,{A:()=>o});var i=n(4008),s=n(579);const o=e=>{let{title:t,content:n,callFn:o}=e;return(0,s.jsx)("div",{style:{position:"fixed",display:"flex",alignItems:"center",justifyContent:"center",left:0,top:0,width:"100%",height:"100%",background:"rgba(0,0,0,0.7)",zIndex:999},children:(0,s.jsxs)("div",{style:{background:"#fff",textAlign:"center",display:"flex",flexDirection:"column",gap:"20px",alignItems:"center",padding:"20px",width:"300px",borderRadius:"10px",fontFamily:"DAEAM_LEE_TAE_JOON",fontSize:"19px"},children:[(0,s.jsx)("div",{style:{paddingBottom:"5px",borderBottom:"1px dashed #8f8f8f"},children:t}),(0,s.jsx)("div",{style:{paddingBottom:"5px",borderBottom:"1px dashed #8f8f8f"},children:n}),(0,s.jsx)("div",{onClick:()=>{o()},children:(0,s.jsx)(i.A,{bttext:"\ud655\uc778"})})]})})}},5349:(e,t,n)=>{n.d(t,{A:()=>o});n(5043);var i=n(4008),s=n(579);const o=e=>{let{title:t,content:n,confirmFn:o,cancelFn:l}=e;return(0,s.jsx)("div",{style:{position:"fixed",display:"flex",alignItems:"center",justifyContent:"center",left:0,top:0,width:"100%",height:"100%",background:"rgba(0,0,0,0.7)",zIndex:999},children:(0,s.jsxs)("div",{style:{background:"#fff",textAlign:"center",display:"flex",flexDirection:"column",gap:"20px",alignItems:"center",padding:"20px",width:"300px",borderRadius:"10px",fontFamily:"DAEAM_LEE_TAE_JOON",fontSize:"19px"},children:[(0,s.jsx)("div",{style:{paddingBottom:"5px",borderBottom:"1px dashed #8f8f8f"},children:t}),(0,s.jsx)("div",{style:{paddingBottom:"5px",borderBottom:"1px dashed #8f8f8f"},children:n}),(0,s.jsxs)("div",{style:{display:"flex",gap:"20px"},children:[(0,s.jsx)("div",{onClick:o,children:(0,s.jsx)(i.A,{bttext:"\ud655\uc778"})}),(0,s.jsx)("div",{onClick:l,children:(0,s.jsx)(i.A,{bttext:"\ucde8\uc18c"})})]})]})})}},2245:(e,t,n)=>{n.d(t,{A:()=>l});var i=n(8095),s=n(255),o=n(579);const l=()=>(0,o.jsx)("div",{style:{position:"relative",width:"80px",height:"80px",background:"rgba(255,255,255,0.8)",display:"flex",flexDirection:"column",gap:"20px",justifyContent:"center",alignItems:"center",borderRadius:"5px",boxShadow:" 4px 4px 4px 0px rgba(0, 0, 0, 0.25)"},children:(0,o.jsx)(i.A,{color:s.J.g200,loading:!0})})},6345:(e,t,n)=>{n.d(t,{A:()=>o});var i=n(5043),s=n(579);const o=e=>{let{src:t,alt:n,placeholder:o,...l}=e;const[a,r]=(0,i.useState)(!1);return(0,s.jsxs)(s.Fragment,{children:[!a&&(0,s.jsx)("div",{style:{width:"100%",height:"100%"},children:o}),(0,s.jsx)("img",{src:t,alt:n,style:{display:a?"block":"none"},onLoad:()=>r(!0),...l})]})}},613:(e,t,n)=>{n.d(t,{A:()=>p});var i=n(5043),s=n(3216),o=n(5475),l=n(8648),a=n(9396);const r=function(){const[e,t]=(0,i.useState)({isOpen:!1,title:"",content:"",confirmFn:null,cancelFn:null});return(0,s.Zp)(),{isSelectModal:e,openSelectModal:(e,n,i,s)=>{t({isOpen:!0,title:e,content:n,confirmFn:i,cancelFn:s})},confirmSelectModal:()=>{t((e=>({...e,isOpen:!1})))},cancelSelectModal:()=>{t((e=>({...e,isOpen:!1})))}}};var c=n(2156);const d=function(){const[e,t]=(0,i.useState)({isOpen:!1,content:"",callFn:null});return(0,s.Zp)(),{isEmptyModal:e,openEmptyModal:(e,n)=>{t({isOpen:!0,content:e,callFn:n})},closeEmptyModal:()=>{t((e=>({...e,isOpen:!1})))}}},p=()=>{const e=(0,s.Zp)(),[t,n]=(0,o.ok)(),{isModal:p,openModal:x,closeModal:h,moveToLogin:m,shutModal:u}=(0,a.A)(),{isLogin:g}=(0,c.A)(),{isSelectModal:f,openSelectModal:v,confirmSelectModal:j,cancelSelectModal:y}=r(),{isEmptyModal:b,openEmptyModal:A,closeEmptyModal:w}=d(),S=t.get("page")?parseInt(t.get("page")):1,k=t.get("search")||"",M=t.get("category")?parseInt(t.get("category")):0,F=t.get("size")?parseInt(t.get("size")):3,E=t.get("filter")?parseInt(t.get("filter")):0,N=t.get("check")?parseInt(t.get("check")):0,O=(0,o.PI)({page:S,search:k,category:M,filter:E,size:F}).toString(),[C,I]=(0,i.useState)(!1);return{page:S,search:k,category:M,check:N,filter:E,MoveToFilter:t=>{let n="";if(t){const e=(0,l.f)(t.filter,E);n=(0,o.PI)({filter:e}).toString()}else n=O;e({pathname:"../list",search:n})},MoveToList:t=>{let n="";if(t){const e=(0,l.f)(t.category,M);n=(0,o.PI)({category:e}).toString()}else n=O;e({pathname:"../list",search:n})},moveToRead:t=>{e({pathname:"../detail/".concat(t),search:O})},moveToSearch:t=>{let n="";if(t){const e=(0,l.f)(t.search,k);n=(0,o.PI)({search:e}).toString(),console.log("queryStr:",n),I(!C)}else n=O;e({pathname:"../list",search:n})},refresh:C,isModal:p,openModal:x,closeModal:h,moveToLogin:m,moveToReser:t=>{e({pathname:"../reservation/".concat(t),search:O})},MoveToPage:t=>{let n="";if(t){const e=(0,l.f)(t.page,S);n=(0,o.PI)({page:e}).toString()}else n=O;e({pathname:"../list",search:n})},moveToReview:(t,n,i,s)=>{console.log(i),e({pathname:"../../meat/review/".concat(t),search:"name=".concat(i,"&checkShop=").concat(n,"&ishop=").concat(s,"&").concat(O)})},isSelectModal:f,openSelectModal:v,confirmSelectModal:j,cancelSelectModal:y,moveToBReser:(t,n,i)=>{t.stopPropagation(),g?e({pathname:"../../butcher/pickup/".concat(n),search:"name=".concat(i,"&").concat(O)}):x("\ub85c\uadf8\uc778 \ud544\uc694","\ub85c\uadf8\uc778\uc774 \ud544\uc694\ud55c \uc11c\ube44\uc2a4\uc785\ub2c8\ub2e4.",m),console.log(i)},openEmptyModal:A,isEmptyModal:b,closeEmptyModal:w,moveToPayment:(t,n,i)=>{console.log("payment, pk :",t,"amount :",n,"checkShop:",i),e({pathname:"/payment/checkout",search:"pk=".concat(t,"&amount=").concat(n,"&checkShop=").concat(i)})},shutModal:u,moveToCheck:t=>{let n="";if(t){const e=(0,l.f)(t.check,N);n=(0,o.PI)({check:e}).toString(),console.log("queryStr:",n),I(!C)}else n=O;e({pathname:"../report",search:n})},size:F,moveToSize:t=>{let n="";if(t){const e=(0,l.f)(t.size,F);n=(0,o.PI)({size:e}).toString(),console.log("queryStr:",n),I(!C)}else n=O;e({pathname:"../review",search:n})}}}},9396:(e,t,n)=>{n.d(t,{A:()=>o});var i=n(5043),s=n(3216);const o=function(){const[e,t]=(0,i.useState)({isOpen:!1,title:"",content:"",callFn:null}),n=(0,s.Zp)();return{isModal:e,openModal:(e,n,i)=>{t({isOpen:!0,title:e,content:n,callFn:i})},closeModal:()=>{t((e=>({...e,isOpen:!1})))},moveToLogin:()=>{t((e=>({...e,isOpen:!1}))),n("/login")},shutModal:()=>{t((e=>({...e,isOpen:!1})))}}}},7173:(e,t,n)=>{n.r(t),n.d(t,{default:()=>q});var i,s,o,l,a,r,c,d,p=n(5043),x=n(3216),h=n(3400),m=n(6957),u=n(5670),g=n(4008),f=n(1719),v=n(4949),j=n(5349),y=n(7475),b=n(7528),A=n(5903),w=n(255);const S=A.A.div(i||(i=(0,b.A)(["\n  position: relative;\n  padding: 30px 60px;\n  display: flex;\n  flex-wrap: wrap;\n  gap: 50px;\n"]))),k=A.A.div(s||(s=(0,b.A)(['\n  position: relative;\n  padding: 30px 20px 20px 20px;\n  display: flex;\n  flex-direction: column;\n  align-items: flex-start;\n  gap: 10px;\n  align-self: stretch;\n  font-family: "DAEAM_LEE_TAE_JOON";\n  font-size: ',";\n  border-bottom: 1px solid ",";\n  @media (max-width: 980px) {\n    display: none;\n  }\n"])),w.Z.strong,w.J.g500),M=A.A.div(o||(o=(0,b.A)(["\n  position: relative;\n  display: flex;\n  align-items: flex-start;\n  gap: 10px;\n"]))),F=A.A.div(l||(l=(0,b.A)(["\n  position: relative;\n  color: ",";\n"])),w.J.g600),E=A.A.div(a||(a=(0,b.A)(["\n  display: flex;\n  align-items: center;\n  gap: 20px;\n  color: ",";\n  font-size: ",";\n  .viewBox {\n    display: flex;\n    align-items: center;\n    gap: 5px;\n  }\n  .like-box {\n    display: flex;\n    align-items: center;\n    gap: 5px;\n    cursor: pointer;\n  }\n  .like-button {\n    background: none;\n    border: none;\n    padding: none;\n    margin: 0;\n    color: ",";\n    font: inherit;\n    cursor: pointer;\n  }\n"])),w.J.g700,w.Z.default,w.J.g700),N=A.A.div(r||(r=(0,b.A)(["\n  position: relative;\n  display: flex;\n  padding: 20px;\n  align-items: flex-start;\n  gap: 60px;\n  align-self: stretch;\n  border-top: 1px solid ",';\n  font-family: "DAEAM_LEE_TAE_JOON";\n  font-size: ',";\n  .prnv {\n    display: flex;\n    align-items: center;\n    gap: 5px;\n  }\n  .prnvTitle {\n    display: flex;\n    align-items: flex-start;\n    color: ",";\n    cursor: pointer;\n  }\n"])),w.J.g500,w.Z.strong,w.J.g700),O=A.A.div(c||(c=(0,b.A)(["\n  display: flex;\n  flex-wrap: wrap;\n  padding: 30px 0px 50px 0px;\n  justify-content: space-between;\n  align-items: flex-start;\n  align-self: stretch;\n  .editBtn {\n    display: flex;\n    align-items: flex-start;\n    gap: 10px;\n    flex: 1 0 0;\n  }\n"]))),C=A.A.div(d||(d=(0,b.A)(["\n  position: relative;\n  display: flex;\n  padding: 20px 20px 50px 20px;\n  flex-direction: column;\n  align-items: flex-start;\n  .readReviewBox {\n    display: flex;\n    flex-wrap: wrap;\n    width: 100%;\n    padding-bottom: 20px;\n    justify-content: space-between;\n    align-items: center;\n    align-self: stretch;\n  }\n  .readReview {\n    display: flex;\n    /* width: 1105px; */\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 15px;\n    flex: 1 0 0;\n  }\n  .reviewInfo {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 8px;\n    align-self: stretch;\n  }\n  .reviewCount {\n    display: flex;\n    flex-direction: column;\n    align-items: flex-start;\n    gap: 8px;\n    align-self: stretch;\n    color: ",";\n    font-size: ",";\n  }\n  .userInfo {\n    display: flex;\n    align-items: center;\n    gap: 30px;\n    align-self: stretch;\n  }\n  .user {\n    display: flex;\n    width: 65px;\n    align-items: center;\n    gap: 2px;\n  }\n  .nickName {\n    align-items: center;\n    flex: 1 0 0;\n    color: ",";\n    font-size: ",";\n  }\n  .date {\n    display: flex;\n    align-items: center;\n    gap: 10px;\n    flex: 1 0 0;\n    color: ",";\n    font-size: 11px;\n  }\n  .reviewContentBox {\n    position: relative;\n    width: 100%;\n    display: flex;\n    justify-content: space-between;\n    align-items: center;\n  }\n  .reviewContent {\n    display: flex;\n    padding: 0px 10px;\n    align-items: center;\n    flex-grow: 1;\n    flex-shrink: 1;\n    gap: 10px;\n    /* align-self: stretch; */\n    color: ",";\n    font-size: ",";\n  }\n  .button-box {\n    display: flex;\n    /* width: 25px; */\n    align-items: center;\n    gap: 8px;\n    flex-shrink: 0;\n  }\n  .deleteBtn {\n    display: flex;\n    width: 25px;\n    align-items: center;\n    gap: 8px;\n    font-size: ",";\n    color: ",";\n    cursor: pointer;\n  }\n  .reportBtn {\n    display: flex;\n    /* width: 25px; */\n    align-items: center;\n    gap: 8px;\n    font-size: ",";\n    color: ",";\n    cursor: pointer;\n  }\n  .inputReviewBox {\n    display: flex;\n    flex-wrap: wrap;\n    padding: 20px 0px;\n    justify-content: space-between;\n    align-items: center;\n    gap: 13px;\n    align-self: stretch;\n    border-top: 1px dashed ",";\n  }\n  .inputReview {\n    display: flex;\n    flex-direction: column;\n    justify-content: center;\n    align-items: center;\n    gap: 10px;\n    flex: 1 0 0;\n    align-self: stretch;\n    textarea {\n      border: none;\n      outline: none;\n      resize: none;\n      padding: 12px 20px 4px 20px;\n      width: 100%;\n      min-width: 220px;\n      height: 90%;\n      border-radius: 10px;\n      border: 1px solid ",";\n      background: #fff;\n      font-size: ",";\n      color: ",";\n    }\n  }\n"])),w.J.secondary,w.Z.default,w.J.primary,w.Z.default,w.J.g500,w.J.g900,w.Z.default,w.Z.default,w.J.primary,w.Z.default,w.J.g700,w.J.g500,w.J.g500,w.Z.default,w.J.g500);var I=n(7097),R=n(8095),T=n(579);const z=()=>(0,T.jsx)("div",{style:{position:"relative",width:"480px",height:"480px",background:"rgba(255,255,255,0.8)",display:"flex",flexDirection:"column",gap:"20px",justifyContent:"center",alignItems:"center",borderRadius:"5px"},children:(0,T.jsx)(R.A,{size:50,color:w.J.g200,loading:!0})});var B=n(613),J=n(2156),P=n(6345),_=n(2245);const L=m.i,D={iboard:0,iuser:0,name:"",writerPic:"",title:"",contents:"",createdAt:"",isFav:0,isReport:0,pics:[],be:{iboard:0,title:""},af:{iboard:0,title:""},comments:[{icomment:0,writerPk:0,writerName:"",comment:"",createdAt:"",isCommentReport:0}]},Z={contents:""},K=()=>{var e;const{userState:t,isSupervisorLogin:n}=(0,J.A)(),i=t.nickname,{moveToRead:s,moveToList:o,moveToModify:l,page:a}=(0,u.A)(),{iboard:r}=(0,x.g)(),[c,d]=(0,p.useState)(D),m=c.name,[b,A]=(0,p.useState)(Z),[w,R]=(0,p.useState)([]),[K,W]=(0,p.useState)(!1),[q,G]=(0,p.useState)(null===(e=c.pics[0])||void 0===e?void 0:e.pic),[U,H]=(0,p.useState)(!1),[$,Q]=(0,p.useState)(!1),[V,X]=(0,p.useState)(null),[Y,ee]=(0,p.useState)(!1),[te,ne]=(0,p.useState)(null),[ie,se]=(0,p.useState)(!1),[oe,le]=(0,p.useState)(!1),[ae,re]=(0,p.useState)(!1),[ce,de]=(0,p.useState)(""),[pe,xe]=(0,p.useState)(""),[he,me]=(0,p.useState)(!1),ue=()=>{(0,h.HD)({iboard:r,successFn:ge,failFn:fe,errorFn:ve})},ge=e=>{W(!1),d(e),R(e.comments),console.log(e)},fe=e=>{W(!1),console.log(e)},ve=e=>{W(!1),console.log(e)};(0,p.useEffect)((()=>{W(!0),ue()}),[r,a]),(0,p.useEffect)((()=>{c.pics&&c.pics.length>0&&c.pics[0].pic&&G(c.pics[0].pic)}),[c.pics]);const je=e=>{console.log(e),me(1),le(!0),de("\ub313\uae00 \ub4f1\ub85d"),xe("\ub313\uae00\uc744 \ub4f1\ub85d\ud558\uc600\uc2b5\ub2c8\ub2e4."),A({...Z}),ue()},ye=e=>{console.log(e),me(1),le(!0),de("\ub313\uae00 \ub4f1\ub85d \uc2e4\ud328"),xe("\ub313\uae00\uc744 \ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4. \ub2e4\uc2dc \ub4f1\ub85d \ud574\uc8fc\uc138\uc694.")},be=e=>{console.log(e),me(1),le(!0),de("\ub313\uae00 \ub4f1\ub85d \uc2e4\ud328"),xe("\uc11c\ubc84\uac00 \ubd88\uc548\uc815\ud569\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \ub4f1\ub85d \ud574\uc8fc\uc138\uc694.")},Ae=e=>{console.log("\ub313\uae00 \uc0ad\uc81c \uc131\uacf5",e),le(!0),de("\ub313\uae00 \uc0ad\uc81c"),xe("\ub313\uae00\uc744 \uc0ad\uc81c\ud558\uc600\uc2b5\ub2c8\ub2e4."),me(1),ue()},we=e=>{console.log("\ub313\uae00 \uc0ad\uc81c \uc2e4\ud328",e),le(!1),de("\ub313\uae00 \uc0ad\uc81c \uc2e4\ud328"),xe("\ub313\uae00 \uc0ad\uc81c\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4. \ub2e4\uc2dc \uc2dc\ub3c4 \ud574\uc8fc\uc138\uc694.")},Se=e=>{console.log("\ub313\uae00 \uc0ad\uc81c \uc2e4\ud328",e),me(1),le(!0),de("\ub313\uae00 \uc0ad\uc81c \uc2e4\ud328"),xe("\uc11c\ubc84\uac00 \ubd88\uc548\uc815\ud569\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4 \ud574\uc8fc\uc138\uc694.")},ke=e=>{console.log("\ud574\ub2f9 \uae00 \uc0ad\uc81c \uc131\uacf5",e),re(!0),de("\ud574\ub2f9 \uae00 \uc0ad\uc81c"),xe("\ud574\ub2f9 \uae00\uc744 \uc0ad\uc81c\ud558\uc600\uc2b5\ub2c8\ub2e4."),me(1),ue()},Me=e=>{console.log("\ud574\ub2f9 \uae00 \uc0ad\uc81c \uc2e4\ud328",e),re(!1),de("\ud574\ub2f9 \uae00 \uc0ad\uc81c \uc2e4\ud328"),xe("\ud574\ub2f9 \uae00 \uc0ad\uc81c\uc5d0 \uc2e4\ud328\ud558\uc600\uc2b5\ub2c8\ub2e4. \ub2e4\uc2dc \uc2dc\ub3c4 \ud574\uc8fc\uc138\uc694.")},Fe=e=>{console.log("\ud574\ub2f9 \uae00 \uc0ad\uc81c \uc2e4\ud328",e),me(1),re(!0),de("\ud574\ub2f9 \uae00 \uc0ad\uc81c \uc2e4\ud328"),xe("\uc11c\ubc84\uac00 \ubd88\uc548\uc815\ud569\ub2c8\ub2e4. \uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4 \ud574\uc8fc\uc138\uc694.")},Ee=t.iuser,[Ne,Oe]=(0,p.useState)(!1),Ce=(0,I.n)({mutationFn:e=>(0,h.cf)({iboard:r}),onSuccess:e=>{0===e.result&&(We("\uc88b\uc544\uc694 \ud574\uc81c","\uc88b\uc544\uc694\uac00 \ud574\uc81c \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",Ge),Oe(!1)),1===e.result&&(We("\uc88b\uc544\uc694 \ub4f1\ub85d","\uc88b\uc544\uc694\uac00 \ub4f1\ub85d \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",Ge),Oe(!0))},onError:()=>{}}),Ie={iboard:r,ireport:1},[Re,Te]=(0,p.useState)(Ie),[ze,Be]=(0,p.useState)(!1),Je=(0,I.n)({mutationFn:e=>(0,h.IH)({reportData:e}),onSuccess:()=>{console.log("\uc2e0\uace0 \uc131\uacf5"),We("\uae00\uc2e0\uace0\uc644\ub8cc","\uc2e0\uace0\uac00 \uc644\ub8cc \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",Ge),Be(!0)},onError:e=>{e.response&&404===e.response.status&&We("\uc2e0\uace0 \uc624\ub958","\uc774\ubbf8 \uc2e0\uace0\ud55c \uae00\uc785\ub2c8\ub2e4.",Ge)}}),Pe=e=>{const t=parseInt(e.target.value,10);Te((e=>({...e,ireport:t})))},_e=()=>{const e={iboard:void 0!==r?Number(r):0,ireport:Re.ireport};console.log("report form test ",e),Je.mutate(e)},[Le,De]=(0,p.useState)({icomment:0,ireport:1}),Ze=(0,I.n)({mutationFn:e=>(0,h.PN)({reportCommentData:e}),onSuccess:e=>{We("\ub313\uae00\uc2e0\uace0\uc644\ub8cc","\uc2e0\uace0\uac00 \uc644\ub8cc \ub418\uc5c8\uc2b5\ub2c8\ub2e4.",Ge)},onError:e=>{e.response&&404===e.response.status&&We("\uc2e0\uace0 \uc624\ub958","\uc774\ubbf8 \uc2e0\uace0\ud55c \uae00\uc785\ub2c8\ub2e4.",Ge)}}),{isModal:Ke,openModal:We,openSelectModal:qe,shutModal:Ge,isSelectModal:Ue,cancelSelectModal:He}=(0,B.A)(),$e=e=>{const t=parseInt(e.target.value,10);console.log("handlechange",t),De((e=>({...e,ireport:t})))},Qe=e=>{console.log("comment num ",e);const t={icomment:void 0!==e?Number(e):0,ireport:Le.ireport};console.log("report form test ",t),Ze.mutate(t)};return(0,T.jsxs)(y.qd,{children:[K?(0,T.jsx)(f.A,{}):null,Ue.isOpen&&(0,T.jsx)(j.A,{title:Ue.title,content:Ue.content,confirmFn:Ue.confirmFn,cancelFn:Ue.cancelFn}),Ke.isOpen&&(0,T.jsx)(v.A,{title:Ke.title,content:Ke.content,callFn:Ke.callFn}),(0,T.jsxs)(k,{children:[(0,T.jsx)(M,{children:c.title}),(0,T.jsxs)(E,{children:[(0,T.jsx)("div",{className:"userName",children:c.name}),(0,T.jsx)("div",{className:"date",children:(Ve=c.createdAt,new Date(Ve).toLocaleString("ko-KR",{year:"numeric",month:"long",day:"numeric",hour:"2-digit",minute:"2-digit",second:"2-digit",hour12:!1}))}),(0,T.jsxs)("div",{className:"like-box",onClick:()=>{console.log("\ub370\uc774\ud130\ub2e4 :",r,Ee),Ce.mutate()},children:[1===(null===c||void 0===c?void 0:c.isFav)?(0,T.jsx)("img",{src:"".concat("","/assets/images/like_fill.svg"),alt:"like"}):(0,T.jsx)("img",{src:"".concat("","/assets/images/like.svg"),alt:"like"}),(0,T.jsx)("div",{children:c.totalFav}),(0,T.jsx)("button",{className:"like-button",children:"\uc88b\uc544\uc694"})]}),(0,T.jsxs)("div",{className:"like-box",onClick:e=>{console.log("\ub538\uae4d",e),qe("\uae00 \uc2e0\uace0\ud558\uae30",(0,T.jsxs)("div",{style:{padding:"10px"},children:[(0,T.jsx)("div",{style:{marginBottom:"20px"},children:(0,T.jsx)("span",{children:"\uc2e0\uace0\ud56d\ubaa9\uc744 \uc120\ud0dd\ud574\uc8fc\uc138\uc694."})}),(0,T.jsx)("div",{children:(0,T.jsxs)("select",{onChange:e=>Pe(e),children:[(0,T.jsx)("option",{value:1,children:"\uc695\uc124/\uc778\uc2e0\uacf5\uaca9"}),(0,T.jsx)("option",{value:2,children:"\uc74c\ub780\ubb3c"}),(0,T.jsx)("option",{value:3,children:"\uc601\ub9ac\ubaa9\uc801/\ud64d\ubcf4\uc131"}),(0,T.jsx)("option",{value:4,children:"\uac1c\uc778\uc815\ubcf4"}),(0,T.jsx)("option",{value:5,children:"\uac8c\uc2dc\uae00 \ub3c4\ubc30"}),(0,T.jsx)("option",{value:6,children:"\uae30\ud0c0"})]})})]}),(()=>{_e(),He()}),(()=>He()))},children:[1===(null===c||void 0===c?void 0:c.isReport)?(0,T.jsx)("img",{src:"".concat("","/assets/images/report_fill.svg"),alt:"like"}):(0,T.jsx)("img",{src:"".concat("","/assets/images/report.svg"),alt:"like"}),(0,T.jsx)("button",{className:"like-button",children:"\uc2e0\uace0\ud558\uae30"})]})]})]}),(0,T.jsxs)(S,{children:[(0,T.jsxs)(y.vP,{children:[(0,T.jsx)(y.ys,{children:c.pics[0]?(0,T.jsx)(P.A,{src:"".concat(L,"/pic/community/").concat(c.iboard,"/").concat(q),alt:"Large image",placeholder:(0,T.jsx)("div",{children:(0,T.jsx)(z,{})})}):null}),(0,T.jsx)(y.g7,{children:c.pics.map(((e,t)=>e&&(0,T.jsx)("div",{className:"thumbnail",onClick:()=>{(e=>{G(e)})(e.pic)},children:(0,T.jsx)(P.A,{src:"".concat(L,"/pic/community/").concat(c.iboard,"/").concat(e.pic),alt:"img_".concat(t+1),placeholder:(0,T.jsx)("div",{children:(0,T.jsx)(_.A,{})})})},t)))})]}),(0,T.jsx)(y.pD,{children:(0,T.jsxs)(y.l$,{children:[(0,T.jsxs)(y.R3,{children:[c.writerPic?(0,T.jsx)("img",{src:"".concat(L,"/pic/user/").concat(c.iuser,"/").concat(c.writerPic),alt:"\ud504\ub85c\ud544\uc0ac\uc9c4"}):(0,T.jsx)("img",{src:"".concat("","/assets/images/user_profile.png"),alt:"\uae30\ubcf8\uc0ac\uc9c4"}),(0,T.jsx)(y.PK,{children:(0,T.jsx)("div",{children:c.name})})]}),(0,T.jsx)(F,{children:c.contents})]})})]}),c.be&&(0,T.jsxs)(N,{children:[(0,T.jsxs)("div",{className:"prnv",children:[(0,T.jsx)("div",{className:"prnvIcon",children:(0,T.jsx)("img",{src:"".concat("","/assets/images/mingcute_up-line.svg"),alt:"img"})}),(0,T.jsx)("div",{className:"prnvText",children:"\uc774\uc804\uae00"})]}),(0,T.jsx)("div",{className:"prnvTitle",onClick:()=>{s(c.be.iboard)},children:c.be.title})]}),c.af&&(0,T.jsxs)(N,{children:[(0,T.jsxs)("div",{className:"prnv",children:[(0,T.jsx)("div",{className:"prnvIcon",children:(0,T.jsx)("img",{src:"".concat("","/assets/images/mingcute_down-line.svg"),alt:"img"})}),(0,T.jsx)("div",{className:"prnvText",children:"\ub2e4\uc74c\uae00"})]}),(0,T.jsx)("div",{className:"prnvTitle",onClick:()=>{s(c.af.iboard)},children:c.af.title})]}),(0,T.jsxs)(O,{children:[(0,T.jsxs)("div",{className:"editBtn",children:[(0,T.jsx)("div",{onClick:()=>{l(c.iboard)},children:m===i||n?(0,T.jsx)(g.A,{bttext:"\uc218\uc815\ud558\uae30"}):null}),(0,T.jsx)("div",{onClick:()=>{(e=>{ne(e),se(!0)})(c.iboard)},children:m===i||n?(0,T.jsx)(g.A,{bttext:"\uc0ad\uc81c\ud558\uae30"}):null})]}),(0,T.jsx)("div",{onClick:()=>{o({page:a})},children:(0,T.jsx)(g.A,{bttext:"\ubaa9\ub85d\ubcf4\uae30"})})]}),(0,T.jsxs)(C,{children:[(0,T.jsx)("div",{className:"readReviewBox",children:(0,T.jsx)("div",{className:"readReview",children:(0,T.jsxs)("div",{className:"reviewInfo",children:[(0,T.jsxs)("div",{className:"reviewCount",children:["\ub313\uae00 ",c.comments.length,"\uac1c"]}),c.comments.length>0&&c.comments.map((e=>(0,T.jsxs)(T.Fragment,{children:[(0,T.jsxs)("div",{className:"userInfo",children:[(0,T.jsxs)("div",{className:"user",children:[(0,T.jsx)("div",{className:"icon",children:(0,T.jsx)("img",{src:"".concat("","/assets/images/speech.svg")})}),(0,T.jsx)("div",{className:"nickName",children:e.writerName})]}),(0,T.jsx)("div",{className:"date",children:e.createdAt})]},e.icomment),(0,T.jsxs)("div",{className:"reviewContentBox",children:[(0,T.jsx)("div",{className:"reviewContent",children:e.comment}),(0,T.jsxs)("div",{className:"button-box",children:[e.writerName===i?(0,T.jsx)("div",{className:"deleteBtn",onClick:()=>{var t;t=e.icomment,X(t),ee(!0)},children:"\uc0ad\uc81c"}):null,1===c.isCommentReport?(0,T.jsx)("div",{children:(0,T.jsx)("div",{className:"reportBtn",onClick:()=>(e=>{console.log("\ub538\uae4d",e),qe("\ub313\uae00 \uc2e0\uace0\ud558\uae30",(0,T.jsxs)("div",{style:{padding:"10px"},children:[(0,T.jsx)("div",{style:{marginBottom:"20px"},children:(0,T.jsx)("span",{children:"\uc2e0\uace0\ud56d\ubaa9\uc744 \uc120\ud0dd\ud574\uc8fc\uc138\uc694."})}),(0,T.jsx)("div",{children:(0,T.jsxs)("select",{onChange:e=>$e(e),children:[(0,T.jsx)("option",{value:1,children:"\uc695\uc124/\uc778\uc2e0\uacf5\uaca9"}),(0,T.jsx)("option",{value:2,children:"\uc74c\ub780\ubb3c"}),(0,T.jsx)("option",{value:3,children:"\uc601\ub9ac\ubaa9\uc801/\ud64d\ubcf4\uc131"}),(0,T.jsx)("option",{value:4,children:"\uac1c\uc778\uc815\ubcf4"}),(0,T.jsx)("option",{value:5,children:"\uac8c\uc2dc\uae00 \ub3c4\ubc30"}),(0,T.jsx)("option",{value:6,children:"\uae30\ud0c0"})]})})]}),(()=>{Qe(e),He()}),(()=>He()))})(e.icomment),children:"\ub313\uae00\uc2e0\uace0"})}):(0,T.jsx)("div",{})]})]})]})))]})})}),(0,T.jsxs)("div",{className:"inputReviewBox",children:[(0,T.jsx)("div",{className:"inputReview",children:(0,T.jsx)("textarea",{cols:50,type:"text",name:"contents",value:b.contents,onChange:e=>(e=>{const t={...b,[e.target.name]:e.target.value};A(t)})(e),placeholder:"\ub313\uae00\uc744 \uc785\ub825\ud574\ubcf4\uc138\uc694(50\uc790 \uc774\ub0b4)"})}),(0,T.jsx)("div",{onClick:()=>{0!==b.contents.length?(b.contents.length<=50&&(0,h.Gy)({iboard:r,contents:b,successFn:je,failFn:ye,errorFn:be}),b.contents.length>50&&Q(!0)):H(!0)},children:(0,T.jsx)(g.A,{bttext:"\ub313\uae00\uc785\ub825"})})]})]}),Y?(0,T.jsx)(j.A,{title:"\ub313\uae00 \uc0ad\uc81c",content:"\uc815\ub9d0 \ub313\uae00\uc744 \uc0ad\uc81c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?",confirmFn:()=>{V&&(0,h.Tu)({icomment:V,successFn:Ae,failFn:we,errorFn:Se}),ee(!1)},cancelFn:()=>{ee(!1)}}):null,ie?(0,T.jsx)(j.A,{title:"\uae00 \uc0ad\uc81c",content:"\uc815\ub9d0 \ud574\ub2f9 \uae00\uc744 \uc0ad\uc81c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?",confirmFn:()=>{te&&(0,h.oK)({iboard:te,successFn:ke,failFn:Me,errorFn:Fe}),se(!1)},cancelFn:()=>{se(!1)}}):null,oe?(0,T.jsx)(v.A,{title:ce,content:pe,callFn:()=>{le(!1)}}):null,ae?(0,T.jsx)(v.A,{title:ce,content:pe,callFn:()=>{se(!1),o({page:a})}}):null,U?(0,T.jsx)(v.A,{title:"\ub313\uae00 \ub4f1\ub85d",content:"\ub313\uae00\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694",callFn:()=>{H(!1)}}):null,$?(0,T.jsx)(v.A,{title:"\ub313\uae00 \ub4f1\ub85d",content:"\ub313\uae00\uc744 50\uc790 \uc774\ub0b4\ub85c \uc791\uc131\ud574\uc8fc\uc138\uc694",callFn:()=>{Q(!1)}}):null]});var Ve};var W=n(947);const q=()=>(0,T.jsxs)("div",{children:[(0,T.jsx)(W.A,{timg:"".concat("","/assets/images/community_header.png"),tname:"\uace0\uae30\uc7a1\ub2f4",tcontent:'"\uc778\uc0dd\uc740 \uace0\uae30\uc11c \uace0\uae30\ub2e4"'}),(0,T.jsx)(K,{})]})},8095:(e,t,n)=>{n.d(t,{A:()=>c});var i=n(5043),s=n(4524),o=n(7628),l=function(){return l=Object.assign||function(e){for(var t,n=1,i=arguments.length;n<i;n++)for(var s in t=arguments[n])Object.prototype.hasOwnProperty.call(t,s)&&(e[s]=t[s]);return e},l.apply(this,arguments)},a=function(e,t){var n={};for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&t.indexOf(i)<0&&(n[i]=e[i]);if(null!=e&&"function"===typeof Object.getOwnPropertySymbols){var s=0;for(i=Object.getOwnPropertySymbols(e);s<i.length;s++)t.indexOf(i[s])<0&&Object.prototype.propertyIsEnumerable.call(e,i[s])&&(n[i[s]]=e[i[s]])}return n},r=(0,o.J)("FadeLoader","50% {opacity: 0.3} 100% {opacity: 1}","fade");const c=function(e){var t=e.loading,n=void 0===t||t,o=e.color,c=void 0===o?"#000000":o,d=e.speedMultiplier,p=void 0===d?1:d,x=e.cssOverride,h=void 0===x?{}:x,m=e.height,u=void 0===m?15:m,g=e.width,f=void 0===g?5:g,v=e.radius,j=void 0===v?2:v,y=e.margin,b=void 0===y?2:y,A=a(e,["loading","color","speedMultiplier","cssOverride","height","width","radius","margin"]),w=(0,s.L)(b).value+18,S=w/2+w/5.5,k=l({display:"inherit",position:"relative",fontSize:"0",top:w,left:w,width:"".concat(3*w,"px"),height:"".concat(3*w,"px")},h),M=function(e){return{position:"absolute",width:(0,s.p)(f),height:(0,s.p)(u),margin:(0,s.p)(b),backgroundColor:c,borderRadius:(0,s.p)(j),transition:"2s",animationFillMode:"both",animation:"".concat(r," ").concat(1.2/p,"s ").concat(.12*e,"s infinite ease-in-out")}},F=l(l({},M(1)),{top:"".concat(w,"px"),left:"0"}),E=l(l({},M(2)),{top:"".concat(S,"px"),left:"".concat(S,"px"),transform:"rotate(-45deg)"}),N=l(l({},M(3)),{top:"0",left:"".concat(w,"px"),transform:"rotate(90deg)"}),O=l(l({},M(4)),{top:"".concat(-1*S,"px"),left:"".concat(S,"px"),transform:"rotate(45deg)"}),C=l(l({},M(5)),{top:"".concat(-1*w,"px"),left:"0"}),I=l(l({},M(6)),{top:"".concat(-1*S,"px"),left:"".concat(-1*S,"px"),transform:"rotate(-45deg)"}),R=l(l({},M(7)),{top:"0",left:"".concat(-1*w,"px"),transform:"rotate(90deg)"}),T=l(l({},M(8)),{top:"".concat(S,"px"),left:"".concat(-1*S,"px"),transform:"rotate(45deg)"});return n?i.createElement("span",l({style:k},A),i.createElement("span",{style:F}),i.createElement("span",{style:E}),i.createElement("span",{style:N}),i.createElement("span",{style:O}),i.createElement("span",{style:C}),i.createElement("span",{style:I}),i.createElement("span",{style:R}),i.createElement("span",{style:T})):null}},7097:(e,t,n)=>{n.d(t,{n:()=>S});var i,s,o,l,a,r,c=n(5043),d=n(9790),p=n(5149),x=n(4137),h=n(1187),m=n(3167),u=n(5239),g=n(1454),f=n(329),v=n(9723),j=(i=new WeakMap,s=new WeakMap,o=new WeakMap,l=new WeakMap,a=new WeakSet,r=new WeakSet,class extends f.Q{constructor(e,t){super(),(0,d.A)(this,r),(0,d.A)(this,a),(0,p.A)(this,i,{writable:!0,value:void 0}),(0,p.A)(this,s,{writable:!0,value:void 0}),(0,p.A)(this,o,{writable:!0,value:void 0}),(0,p.A)(this,l,{writable:!0,value:void 0}),(0,m.A)(this,i,e),this.setOptions(t),this.bindMethods(),(0,h.A)(this,a,y).call(this)}bindMethods(){this.mutate=this.mutate.bind(this),this.reset=this.reset.bind(this)}setOptions(e){const t=this.options;var n;(this.options=(0,x.A)(this,i).defaultMutationOptions(e),(0,v.f8)(this.options,t)||(0,x.A)(this,i).getMutationCache().notify({type:"observerOptionsUpdated",mutation:(0,x.A)(this,o),observer:this}),null!==t&&void 0!==t&&t.mutationKey&&this.options.mutationKey&&(0,v.EN)(t.mutationKey)!==(0,v.EN)(this.options.mutationKey))?this.reset():null===(n=(0,x.A)(this,o))||void 0===n||n.setOptions(this.options)}onUnsubscribe(){var e;this.hasListeners()||(null===(e=(0,x.A)(this,o))||void 0===e||e.removeObserver(this))}onMutationUpdate(e){(0,h.A)(this,a,y).call(this),(0,h.A)(this,r,b).call(this,e)}getCurrentResult(){return(0,x.A)(this,s)}reset(){var e;null===(e=(0,x.A)(this,o))||void 0===e||e.removeObserver(this),(0,m.A)(this,o,void 0),(0,h.A)(this,a,y).call(this),(0,h.A)(this,r,b).call(this)}mutate(e,t){var n;return(0,m.A)(this,l,t),null===(n=(0,x.A)(this,o))||void 0===n||n.removeObserver(this),(0,m.A)(this,o,(0,x.A)(this,i).getMutationCache().build((0,x.A)(this,i),this.options)),(0,x.A)(this,o).addObserver(this),(0,x.A)(this,o).execute(e)}});function y(){var e,t;const n=null!==(e=null===(t=(0,x.A)(this,o))||void 0===t?void 0:t.state)&&void 0!==e?e:(0,u.$)();(0,m.A)(this,s,{...n,isPending:"pending"===n.status,isSuccess:"success"===n.status,isError:"error"===n.status,isIdle:"idle"===n.status,mutate:this.mutate,reset:this.reset})}function b(e){g.j.batch((()=>{if((0,x.A)(this,l)&&this.hasListeners()){const p=(0,x.A)(this,s).variables,h=(0,x.A)(this,s).context;var t,n,i,o;if("success"===(null===e||void 0===e?void 0:e.type))null===(t=(n=(0,x.A)(this,l)).onSuccess)||void 0===t||t.call(n,e.data,p,h),null===(i=(o=(0,x.A)(this,l)).onSettled)||void 0===i||i.call(o,e.data,null,p,h);else if("error"===(null===e||void 0===e?void 0:e.type)){var a,r,c,d;null===(a=(r=(0,x.A)(this,l)).onError)||void 0===a||a.call(r,e.error,p,h),null===(c=(d=(0,x.A)(this,l)).onSettled)||void 0===c||c.call(d,void 0,e.error,p,h)}}this.listeners.forEach((e=>{e((0,x.A)(this,s))}))}))}var A=n(3248),w=n(3247);function S(e,t){const n=(0,A.jE)(t),[i]=c.useState((()=>new j(n,e)));c.useEffect((()=>{i.setOptions(e)}),[i,e]);const s=c.useSyncExternalStore(c.useCallback((e=>i.subscribe(g.j.batchCalls(e))),[i]),(()=>i.getCurrentResult()),(()=>i.getCurrentResult())),o=c.useCallback(((e,t)=>{i.mutate(e,t).catch(k)}),[i]);if(s.error&&(0,w.G)(i.options.throwOnError,[s.error]))throw s.error;return{...s,mutate:o,mutateAsync:s.mutate}}function k(){}},3247:(e,t,n)=>{function i(e,t){return"function"===typeof e?e(...t):!!e}n.d(t,{G:()=>i})}}]);
//# sourceMappingURL=7173.42f6133b.chunk.js.map
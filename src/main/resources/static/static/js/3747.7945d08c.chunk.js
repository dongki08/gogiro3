"use strict";(self.webpackChunkteam1_project=self.webpackChunkteam1_project||[]).push([[3747],{3747:(t,e,s)=>{s.d(e,{I:()=>ut});var i,n,r,a,h,o,l,u,c,d,A,p,f,v,b,w,y,R,g,k,O,C,m,S=s(9790),W=s(5149),U=s(1187),M=s(4137),E=s(3167),Q=s(9723),F=s(1454),T=s(3491),j=s(329),D=s(2463),I=(i=new WeakMap,n=new WeakMap,r=new WeakMap,a=new WeakMap,h=new WeakMap,o=new WeakMap,l=new WeakMap,u=new WeakMap,c=new WeakMap,d=new WeakMap,A=new WeakMap,p=new WeakMap,f=new WeakMap,v=new WeakMap,b=new WeakSet,w=new WeakSet,y=new WeakSet,R=new WeakSet,g=new WeakSet,k=new WeakSet,O=new WeakSet,C=new WeakSet,m=new WeakSet,class extends j.Q{constructor(t,e){super(),(0,S.A)(this,m),(0,S.A)(this,C),(0,S.A)(this,O),(0,S.A)(this,k),(0,S.A)(this,g),(0,S.A)(this,R),(0,S.A)(this,y),(0,S.A)(this,w),(0,S.A)(this,b),(0,W.A)(this,i,{writable:!0,value:void 0}),(0,W.A)(this,n,{writable:!0,value:void 0}),(0,W.A)(this,r,{writable:!0,value:void 0}),(0,W.A)(this,a,{writable:!0,value:void 0}),(0,W.A)(this,h,{writable:!0,value:void 0}),(0,W.A)(this,o,{writable:!0,value:void 0}),(0,W.A)(this,l,{writable:!0,value:void 0}),(0,W.A)(this,u,{writable:!0,value:void 0}),(0,W.A)(this,c,{writable:!0,value:void 0}),(0,W.A)(this,d,{writable:!0,value:void 0}),(0,W.A)(this,A,{writable:!0,value:void 0}),(0,W.A)(this,p,{writable:!0,value:void 0}),(0,W.A)(this,f,{writable:!0,value:void 0}),(0,W.A)(this,v,{writable:!0,value:new Set}),this.options=e,(0,E.A)(this,i,t),(0,E.A)(this,l,null),this.bindMethods(),this.setOptions(e)}bindMethods(){this.refetch=this.refetch.bind(this)}onSubscribe(){1===this.listeners.size&&((0,M.A)(this,n).addObserver(this),H((0,M.A)(this,n),this.options)?(0,U.A)(this,b,P).call(this):this.updateResult(),(0,U.A)(this,g,q).call(this))}onUnsubscribe(){this.hasListeners()||this.destroy()}shouldFetchOnReconnect(){return J((0,M.A)(this,n),this.options,this.options.refetchOnReconnect)}shouldFetchOnWindowFocus(){return J((0,M.A)(this,n),this.options,this.options.refetchOnWindowFocus)}destroy(){this.listeners=new Set,(0,U.A)(this,k,B).call(this),(0,U.A)(this,O,z).call(this),(0,M.A)(this,n).removeObserver(this)}setOptions(t,e){const s=this.options,r=(0,M.A)(this,n);if(this.options=(0,M.A)(this,i).defaultQueryOptions(t),"undefined"!==typeof this.options.enabled&&"boolean"!==typeof this.options.enabled)throw new Error("Expected enabled to be a boolean");(0,U.A)(this,C,G).call(this),(0,Q.f8)(this.options,s)||(0,M.A)(this,i).getQueryCache().notify({type:"observerOptionsUpdated",query:(0,M.A)(this,n),observer:this});const a=this.hasListeners();a&&K((0,M.A)(this,n),r,this.options,s)&&(0,U.A)(this,b,P).call(this),this.updateResult(e),!a||(0,M.A)(this,n)===r&&this.options.enabled===s.enabled&&this.options.staleTime===s.staleTime||(0,U.A)(this,w,L).call(this);const h=(0,U.A)(this,y,x).call(this);!a||(0,M.A)(this,n)===r&&this.options.enabled===s.enabled&&h===(0,M.A)(this,f)||(0,U.A)(this,R,_).call(this,h)}getOptimisticResult(t){const e=(0,M.A)(this,i).getQueryCache().build((0,M.A)(this,i),t),s=this.createResult(e,t);return function(t,e){if(!(0,Q.f8)(t.getCurrentResult(),e))return!0;return!1}(this,s)&&((0,E.A)(this,a,s),(0,E.A)(this,o,this.options),(0,E.A)(this,h,(0,M.A)(this,n).state)),s}getCurrentResult(){return(0,M.A)(this,a)}trackResult(t){const e={};return Object.keys(t).forEach((s=>{Object.defineProperty(e,s,{configurable:!1,enumerable:!0,get:()=>((0,M.A)(this,v).add(s),t[s])})})),e}getCurrentQuery(){return(0,M.A)(this,n)}refetch(){let{...t}=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return this.fetch({...t})}fetchOptimistic(t){const e=(0,M.A)(this,i).defaultQueryOptions(t),s=(0,M.A)(this,i).getQueryCache().build((0,M.A)(this,i),e);return s.isFetchingOptimistic=!0,s.fetch().then((()=>this.createResult(s,e)))}fetch(t){var e;return(0,U.A)(this,b,P).call(this,{...t,cancelRefetch:null===(e=t.cancelRefetch)||void 0===e||e}).then((()=>(this.updateResult(),(0,M.A)(this,a))))}createResult(t,e){const s=(0,M.A)(this,n),i=this.options,A=(0,M.A)(this,a),p=(0,M.A)(this,h),f=(0,M.A)(this,o),v=t!==s?t.state:(0,M.A)(this,r),{state:b}=t;let w,{error:y,errorUpdatedAt:R,fetchStatus:g,status:k}=b,O=!1;if(e._optimisticResults){const n=this.hasListeners(),r=!n&&H(t,e),a=n&&K(t,s,e,i);(r||a)&&(g=(0,D.v_)(t.options.networkMode)?"fetching":"paused",b.dataUpdatedAt||(k="pending")),"isRestoring"===e._optimisticResults&&(g="idle")}if(e.select&&"undefined"!==typeof b.data)if(A&&b.data===(null===p||void 0===p?void 0:p.data)&&e.select===(0,M.A)(this,u))w=(0,M.A)(this,c);else try{(0,E.A)(this,u,e.select),w=e.select(b.data),w=(0,Q.pl)(null===A||void 0===A?void 0:A.data,w,e),(0,E.A)(this,c,w),(0,E.A)(this,l,null)}catch(F){(0,E.A)(this,l,F)}else w=b.data;if("undefined"!==typeof e.placeholderData&&"undefined"===typeof w&&"pending"===k){let t;var C;if(null!==A&&void 0!==A&&A.isPlaceholderData&&e.placeholderData===(null===f||void 0===f?void 0:f.placeholderData))t=A.data;else if(t="function"===typeof e.placeholderData?e.placeholderData(null===(C=(0,M.A)(this,d))||void 0===C?void 0:C.state.data,(0,M.A)(this,d)):e.placeholderData,e.select&&"undefined"!==typeof t)try{t=e.select(t),(0,E.A)(this,l,null)}catch(F){(0,E.A)(this,l,F)}"undefined"!==typeof t&&(k="success",w=(0,Q.pl)(null===A||void 0===A?void 0:A.data,t,e),O=!0)}(0,M.A)(this,l)&&(y=(0,M.A)(this,l),w=(0,M.A)(this,c),R=Date.now(),k="error");const m="fetching"===g,S="pending"===k,W="error"===k,U=S&&m;return{status:k,fetchStatus:g,isPending:S,isSuccess:"success"===k,isError:W,isInitialLoading:U,isLoading:U,data:w,dataUpdatedAt:b.dataUpdatedAt,error:y,errorUpdatedAt:R,failureCount:b.fetchFailureCount,failureReason:b.fetchFailureReason,errorUpdateCount:b.errorUpdateCount,isFetched:b.dataUpdateCount>0||b.errorUpdateCount>0,isFetchedAfterMount:b.dataUpdateCount>v.dataUpdateCount||b.errorUpdateCount>v.errorUpdateCount,isFetching:m,isRefetching:m&&!S,isLoadingError:W&&0===b.dataUpdatedAt,isPaused:"paused"===g,isPlaceholderData:O,isRefetchError:W&&0!==b.dataUpdatedAt,isStale:N(t,e),refetch:this.refetch}}updateResult(t){const e=(0,M.A)(this,a),s=this.createResult((0,M.A)(this,n),this.options);if((0,E.A)(this,h,(0,M.A)(this,n).state),(0,E.A)(this,o,this.options),void 0!==(0,M.A)(this,h).data&&(0,E.A)(this,d,(0,M.A)(this,n)),(0,Q.f8)(s,e))return;(0,E.A)(this,a,s);const i={};!1!==(null===t||void 0===t?void 0:t.listeners)&&(()=>{if(!e)return!0;const{notifyOnChangeProps:t}=this.options,s="function"===typeof t?t():t;if("all"===s||!s&&!(0,M.A)(this,v).size)return!0;const i=new Set(null!==s&&void 0!==s?s:(0,M.A)(this,v));return this.options.throwOnError&&i.add("error"),Object.keys((0,M.A)(this,a)).some((t=>{const s=t;return(0,M.A)(this,a)[s]!==e[s]&&i.has(s)}))})()&&(i.listeners=!0),(0,U.A)(this,m,$).call(this,{...i,...t})}onQueryUpdate(){this.updateResult(),this.hasListeners()&&(0,U.A)(this,g,q).call(this)}});function P(t){(0,U.A)(this,C,G).call(this);let e=(0,M.A)(this,n).fetch(this.options,t);return null!==t&&void 0!==t&&t.throwOnError||(e=e.catch(Q.lQ)),e}function L(){if((0,U.A)(this,k,B).call(this),Q.S$||(0,M.A)(this,a).isStale||!(0,Q.gn)(this.options.staleTime))return;const t=(0,Q.j3)((0,M.A)(this,a).dataUpdatedAt,this.options.staleTime)+1;(0,E.A)(this,A,setTimeout((()=>{(0,M.A)(this,a).isStale||this.updateResult()}),t))}function x(){var t;return null!==(t="function"===typeof this.options.refetchInterval?this.options.refetchInterval((0,M.A)(this,n)):this.options.refetchInterval)&&void 0!==t&&t}function _(t){(0,U.A)(this,O,z).call(this),(0,E.A)(this,f,t),!Q.S$&&!1!==this.options.enabled&&(0,Q.gn)((0,M.A)(this,f))&&0!==(0,M.A)(this,f)&&(0,E.A)(this,p,setInterval((()=>{(this.options.refetchIntervalInBackground||T.m.isFocused())&&(0,U.A)(this,b,P).call(this)}),(0,M.A)(this,f)))}function q(){(0,U.A)(this,w,L).call(this),(0,U.A)(this,R,_).call(this,(0,U.A)(this,y,x).call(this))}function B(){(0,M.A)(this,A)&&(clearTimeout((0,M.A)(this,A)),(0,E.A)(this,A,void 0))}function z(){(0,M.A)(this,p)&&(clearInterval((0,M.A)(this,p)),(0,E.A)(this,p,void 0))}function G(){const t=(0,M.A)(this,i).getQueryCache().build((0,M.A)(this,i),this.options);if(t===(0,M.A)(this,n))return;const e=(0,M.A)(this,n);(0,E.A)(this,n,t),(0,E.A)(this,r,t.state),this.hasListeners()&&(null===e||void 0===e||e.removeObserver(this),t.addObserver(this))}function $(t){F.j.batch((()=>{t.listeners&&this.listeners.forEach((t=>{t((0,M.A)(this,a))})),(0,M.A)(this,i).getQueryCache().notify({query:(0,M.A)(this,n),type:"observerResultsUpdated"})}))}function H(t,e){return function(t,e){return!1!==e.enabled&&!t.state.dataUpdatedAt&&!("error"===t.state.status&&!1===e.retryOnMount)}(t,e)||t.state.dataUpdatedAt>0&&J(t,e,e.refetchOnMount)}function J(t,e,s){if(!1!==e.enabled){const i="function"===typeof s?s(t):s;return"always"===i||!1!==i&&N(t,e)}return!1}function K(t,e,s,i){return!1!==s.enabled&&(t!==e||!1===i.enabled)&&(!s.suspense||"error"!==t.state.status)&&N(t,s)}function N(t,e){return t.isStaleByTime(e.staleTime)}var V=s(5043);s(579);function X(){let t=!1;return{clearReset:()=>{t=!1},reset:()=>{t=!0},isReset:()=>t}}var Y=V.createContext(X()),Z=()=>V.useContext(Y),tt=s(3248),et=V.createContext(!1),st=()=>V.useContext(et),it=(et.Provider,s(3247)),nt=(t,e)=>{(t.suspense||t.throwOnError)&&(e.isReset()||(t.retryOnMount=!1))},rt=t=>{V.useEffect((()=>{t.clearReset()}),[t])},at=t=>{let{result:e,errorResetBoundary:s,throwOnError:i,query:n}=t;return e.isError&&!s.isReset()&&!e.isFetching&&n&&(0,it.G)(i,[e.error,n])},ht=t=>{t.suspense&&"number"!==typeof t.staleTime&&(t.staleTime=1e3)},ot=(t,e)=>(null===t||void 0===t?void 0:t.suspense)&&e.isPending,lt=(t,e,s)=>e.fetchOptimistic(t).catch((()=>{s.clearReset()}));function ut(t,e){return function(t,e,s){const i=(0,tt.jE)(s),n=st(),r=Z(),a=i.defaultQueryOptions(t);a._optimisticResults=n?"isRestoring":"optimistic",ht(a),nt(a,r),rt(r);const[h]=V.useState((()=>new e(i,a))),o=h.getOptimisticResult(a);if(V.useSyncExternalStore(V.useCallback((t=>{const e=n?()=>{}:h.subscribe(F.j.batchCalls(t));return h.updateResult(),e}),[h,n]),(()=>h.getCurrentResult()),(()=>h.getCurrentResult())),V.useEffect((()=>{h.setOptions(a,{listeners:!1})}),[a,h]),ot(a,o))throw lt(a,h,r);if(at({result:o,errorResetBoundary:r,throwOnError:a.throwOnError,query:i.getQueryCache().get(a.queryHash)}))throw o.error;return a.notifyOnChangeProps?o:h.trackResult(o)}(t,I,e)}},3247:(t,e,s)=>{function i(t,e){return"function"===typeof t?t(...e):!!t}s.d(e,{G:()=>i})}}]);
//# sourceMappingURL=3747.7945d08c.chunk.js.map
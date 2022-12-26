function jQuery(selector) {
  let el = [];  
  if (selector.startsWith("<")) {
    el[0] = document.createElement(selector.substring(1, selector.length - 1));
  } else {
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      el.push(e);
      }
  }

  
  el.append = function(childBox) {
    for (let e of el) {
      for (let child of childBox) {
        e.appendChild(child.cloneNode('deep'));        
      }
    }

    for (let child of childBox) {
      if (child.parentElement != null || child.parentElement != undefined) {
        child.parentElement.removeChild(child);
      }
    }    
  }; 

  el.html = function(content) { 
    for (let e of el) {
      e.innerHTML = content;
    }
  }

  el.on = function(eventName, listener) {
    for (let e of el) {
      e.addEventListener(eventName,listener);
    }
  }


  return el;
}

var $ = jQuery;

function jQuery(selector) {
  return new ElementBox(selector);
}

jQuery.ajax = function(settings) {
  if (settings.method == undefined) settings.method = "GET";
  if (settings.async == undefined) settings.async = true;
  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = () => {
    if (xhr.readyState == 4) { 
        if (xhr.status == 200) {
          if (settings.success == undefined) return;
          let result;
          if (settings.dataType == "json") {
            result = JSON.parse(xhr.responseText);
          } else {
            result = xhr.responseText;
          }
          settings.success(result);
          
        } else {
          if (settings.error == undefined) return;
          settings.error();
        }
      }
    };
    xhr.open(settings.method, settings.url, settings.async);

    if (settings.method == "POST") {
      xhr.setRequestHeader(
        "Content-Type", 
        "application/x-www-form-urlencoded");  
        let payload = "";
        if (settings.data != undefined && settings.data != null) {
          for (let key in settings.data) {
            if (payload.length > 0) {
              payload += "&";
            }
            payload += key + "=" + encodeURIComponent(settings.data[key]);
          }
        }
        xhr.send(payload);
      } else {
        xhr.send();
        
      }
      
};

jQuery.getJSON = function(url, success) {
  jQuery.ajax({
    url:url,
    dataType:"json",
    success: success});
};

jQuery.post = function(url, data, success, dataType) {
  jQuery.ajax({
    method:"POST",
    url:url,
    dataType:dataType,
    success:success,
    data:data,
  });
};


var $ = jQuery;

function ElementBox(selector) {
  this.el = [];
  if (selector.startsWith("<")) {
    this.el[0] = document.createElement(selector.substring(1, selector.length - 1));
  } else {
    let nodeList = document.querySelectorAll(selector);
    for (let e of nodeList) {
      this.el.push(e);
    }
  }
  return this;
}
ElementBox.prototype.append = function(childBox) {
  for (let e of this.el) {
    for (let child of childBox) {
      e.appendChild(child.cloneNode('deep'));
    }
  }

  for (let child of childBox) {
    if (child.parentElement != null || child.parentElement != undefined) {
      child.parentElement.removeChild(child);
    } 
  }
  return this;
  
};
ElementBox.prototype.appendTo = function(parents) {
  for (let e of parents.el) {
    for (let child of this.el) {
      e.appendChild(child.cloneNode('deep'));
    }
  }

  for (let child of this.el) {
    if (child.parentElement != null || child.parentElement != undefined) {
      child.parentElement.removeChild(child);
    }
  }
  return this;
};

ElementBox.prototype.html = function(content) {
  if (this.el.length == 0) {
    return;
  }

  if (arguments.length > 0) {
    for (let e of this.el) {
      e.innerHTML = content;
    }
    return this;
  } else {
    return  this.el[0].innerHTML;
  }
  
};


ElementBox.prototype.on = function(eventName, listener) {
  for (let e of this.el) {
    e.addEventListener(eventName, listener);
  }
  return this;
};

ElementBox.prototype.click = function(handler) {
  return this.on('click', handler);    
}

ElementBox.prototype.val = function(value) {
  if (this.el.length == 0) {
    return;
  }

  if (arguments.length > 0) {
    for (let e of this.el) {
      e.value = value;
    }
    return this;
  } else {
    return this.el[0].value;
  }
}

ElementBox.prototype.submit = function(handler) {
  return this.on('submit', handler);    
}
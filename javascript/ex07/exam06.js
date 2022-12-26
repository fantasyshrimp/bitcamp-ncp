ElementBox.prototype.button = function(selector) {
  this.el.forEach((e) => {
    e.classList.add("btn");   
  });
};

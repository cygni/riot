var RiotTextArea = Class.create({
	initialize: function(el) {
		this.el = $(el);
	},
	setMaxLength: function(maxlength) {
		this.maxLength = maxlength;
		if (!this.counter) {
			var c = this.counter = RBuilder.node('div', {className: 'counter'}).insertSelfAfter(this.el);
			this.el.observe('keypress', this.checkLength.bindAsEventListener(this));
			this.el.observe('keyup', this.updateCounter.bind(this));
		}
		this.updateCounter();
		return this;
	},
	checkLength: function(ev) {
		if(this.el.value.length >= this.maxLength && (ev.keyCode == 0 || ev.keyCode == 13 || ev.keyCode >= 65)) {
			Event.stop(ev);
		}
	},
	updateCounter: function() {
		if (this.el.value.length > this.maxLength) {
			this.el.value = this.el.value.substring(0, this.maxLength);
		}
		this.counter.update(this.el.value.length + '/' + this.maxLength);
	},
	autoResize: function(maxHeight) {
		if (this.autoSize) return;
		this.maxHeight = maxHeight || 300;
        var border = parseInt(this.el.getStyle('borderTopWidth')) + parseInt(this.el.getStyle('borderBottomWidth'));
		var padding = parseInt(this.el.getStyle('paddingTop')) + parseInt(this.el.getStyle('paddingBottom'));
		this.maxHeight -= (this.maxHeight - border - padding) % parseInt(this.el.getStyle('lineHeight'));
		this.measure = RBuilder.node('div').cloneStyle(this.el, [
			'paddingTop', 'paddingRight', 'paddingBottom', 
			'paddingLeft', 'lineHeight', 'fontSize', 'fontFamily',
            'borderTopWidth', 'borderRightWidth', 'borderBottomWidth', 'borderLeftWidth'
		]).setStyle({
			visibility: 'hidden',
            borderStyle: 'solid',
			position: 'absolute'
		});
		this.el.wrap(RBuilder.node('div', {className: 'textarea-wrapper'}).setStyle({position: 'relative'})).prependChild(this.measure);
		this.el.observe('keyup', this.resize.bind(this));
		this.resize();
		this.autoSize = true;
		return this;
	},
	resize: function() {
		this.measure.innerHTML = this.el.value.gsub(/</, '&lt;').gsub(/\n/, '<br/>').gsub(/  /, '&nbsp; ') + '<br>&nbsp;';
		this.el.style.height = Math.min(this.measure.offsetHeight, this.maxHeight) + 'px';
	}
});

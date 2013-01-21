/*	
	Add maxlength to Textarea
	Copyright (c) 2010 CS Truter
	Written by Christoff Truter
	Email: christoff@cstruter.com
*/

function limiter()
{
	var textareas = document.getElementsByTagName('textarea');
	
	for(var i = 0; i < textareas.length; i++)
	{
		var textarea = textareas.item(i);
		limit(textarea);
	}
}

function limit(sender)
{
	var maxlength = sender.getAttribute('maxlength');
	if (maxlength != null)
	{
		// limit input values
		sender.onkeypress = function(e)
		{					
			// Crossbrowser Issue 
			if (e == null)
				e = window.event;
			
			// exclude certain keys from our limiter
			if ((e.keyCode == 8) || 
				(e.keyCode > 36) && 
				(e.keyCode < 41)) return true;
				
			return (sender.value.length < maxlength);
		}
		
		// limit pasted values
		sender.onpaste = function()
		{
			// onAfterPaste
			setTimeout(function()
			{
				if(sender.value.length > maxlength) {
					sender.value = sender.value.substring(0, maxlength);
				}							
			}, 1);
		}
	}
}

// Only Attach events needed for limiting the textareas once the page finished loading
if (window.addEventListener) { // FF etc
	window.addEventListener('load', limiter, false);
}
else{ // IE
	window.attachEvent('onload', limiter);
}
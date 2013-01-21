#import('dart:dom');

void LimitTextAreas()
{
  NodeList nodes = window.document.getElementsByTagName("textarea");  
  for (int i = 0; i < nodes.length;i++)
    {
      HTMLTextAreaElement obj = nodes[i];
      Node node = obj.attributes.getNamedItem("makslengte");
      if (node != null)
      {
        int maxlength = Math.parseInt(node.nodeValue);
        obj.addEventListener("keypress", (KeyboardEvent e) {
          if (((e.keyCode == 8) || (e.keyCode > 36) && (e.keyCode < 41)) 
              || (obj.value.length < maxlength)) return;        
            e.preventDefault();
        });
        obj.addEventListener("paste", (Event e){      
          window.setTimeout((){        
            if(obj.value.length > maxlength)
              obj.value = obj.value.substring(0, maxlength);
          }, 1);
        });
      }
    }
}

void main() {
  LimitTextAreas();
}

var captions = captions_2013.before;
var index = Math.floor(Math.random() * captions.length);
var text = captions[index];
document.getElementById('caption').innerHTML = text;
var editor = {
    _textField: document.getElementById('editor');

    init: function() {
        this._textField.addEventListener("keydown", e => {
            let BACKSPACE = 8;

            if (e.which == BACKSPACE) {
                if (editor._textField.innerText.length == 1) {
                    e.preventDefault();
                    return false;
                }
            }
        });
    }
}
document.addEventListener('keydown', function(event) {
    const textarea = document.querySelector('textarea');
    if (textarea && event.ctrlKey) {
        switch (event.key) {
            case 'b':
                insertAtCursor(textarea, 'INT. ');
                event.preventDefault();
                break;
            case 'c':
                insertAtCursor(textarea, 'CUT TO: ');
                event.preventDefault();
                break;
            case 'd':
                insertAtCursor(textarea, 'DIALOGUE');
                event.preventDefault();
                break;
        }
    }
});

function insertAtCursor(textarea, text) {
    const start = textarea.selectionStart;
    const end = textarea.selectionEnd;
    const before = textarea.value.substring(0, start);
    const after = textarea.value.substring(end, textarea.value.length);
    textarea.value = before + text + after;
    textarea.selectionStart = textarea.selectionEnd = start + text.length;
    textarea.focus();
}

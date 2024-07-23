document.addEventListener('DOMContentLoaded', function() {
    const textarea = document.querySelector('textarea');
    textarea.addEventListener('input', function() {
        checkSpelling(textarea);
    });
});

function checkSpelling(textarea) {
    const words = textarea.value.split(/\s+/);
    const misspelled = [];

    for (const word of words) {
        if (!isCorrectlySpelled(word)) {
            misspelled.push(word);
        }
    }

    highlightMisspelledWords(textarea, misspelled);
}

function isCorrectlySpelled(word) {
    const dictionary = ['INT.', 'EXT.', 'CUT TO:', 'DIALOGUE']; // Add more words as needed
    return dictionary.includes(word.toUpperCase());
}

function highlightMisspelledWords(textarea, misspelled) {
    const content = textarea.value;
    const highlightedContent = content.replace(/\b(\w+)\b/g, function(match) {
        return misspelled.includes(match) ? `<span class="misspelled">${match}</span>` : match;
    });
    const div = document.createElement('div');
    div.innerHTML = highlightedContent;
    textarea.parentNode.insertBefore(div, textarea.nextSibling);
    textarea.style.display = 'none';
}

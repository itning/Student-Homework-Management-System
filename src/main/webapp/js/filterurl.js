function isValidURL(string) {
    var res = string.match(/(http(s)?:\/\/.)?(www\.)?[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)/g);
    return (res !== null)
};

function checkSetURL(classstr){
    var teststr = $(classstr).html();

    if (teststr[0] !== '[' || teststr[teststr.length-1] !== ')'){
        // not a link
    } else {
        // check if there's many [] () s
        var count = 0;
        count += (teststr.match(/\[/g) || []).length;
        count += (teststr.match(/\]/g) || []).length;
        count += (teststr.match(/\(/g) || []).length;
        count += (teststr.match(/\)/g) || []).length;

        if (count === 4){
            // possiblely a markdown url link

            // get substring between
            var purl = teststr.substring(
                teststr.lastIndexOf('(') + 1,
                teststr.lastIndexOf(')')
            );

            var ptext = teststr.substring(
                teststr.lastIndexOf('[') + 1,
                teststr.lastIndexOf(']')
            );

            if (isValidURL(purl)){
                $(classstr).html("<a href=\"" + purl + "\" target=\"_blank\">" + ptext + "</a>");
            }
        }
    }
}

function checkSetURLGivenStr(teststr){
    if (teststr[0] !== '[' || teststr[teststr.length-1] !== ')'){
        // not a link
    } else {
        // check if there's many [] () s
        var count = 0;
        count += (teststr.match(/\[/g) || []).length;
        count += (teststr.match(/\]/g) || []).length;
        count += (teststr.match(/\(/g) || []).length;
        count += (teststr.match(/\)/g) || []).length;

        if (count === 4){
            // possiblely a markdown url link

            // get substring between
            var purl = teststr.substring(
                teststr.lastIndexOf('(') + 1,
                teststr.lastIndexOf(')')
            );

            var ptext = teststr.substring(
                teststr.lastIndexOf('[') + 1,
                teststr.lastIndexOf(']')
            );

            if (isValidURL(purl)){
                return "<a href=\"" + purl + "\" target=\"_blank\">" + ptext + "</a>";
            }
        }
    }

    return null;
}
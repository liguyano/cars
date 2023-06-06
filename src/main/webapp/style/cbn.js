let choice=[[0,0,0,0,0,1,1,1,1,1,1,1,1],[0,0,0,1,1,1,1,2,3],[0,0,0,0,0,1,1,2,3],[0,0,0,1,1,2,3,2,2,3,4],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],[0,0,0,0,0,1,1,2,3],[0,0,0,0,0,1,1,2,3],[0,1,2,1,2],[0,0,1,1,1,1,2,3],[0,0,1,1,1,2,2,2,2,3,4],[0,0,0,0,0,0,0,0,0,0,1,1,1],[0],[0],[0],[0],[0],[0]]
let multi=[3,3,2,2,2,2];
let alques=$(".field");
let muNow=-1;
function getRandomElementFromList(list) {
    var randomIndex = Math.floor(Math.random() * list.length);
    return list[randomIndex];
}
function getRandomUniqueElementsFromList(list, n) {
    if (n > list.length) {
        console.log("Error: The requested number of elements exceeds the list size.");
        return [];
    }
    var result = [];
    var tempList = list.slice(); // 创建列表的副本

    for (var i = 0; i < n; i++) {
        var randomIndex = Math.floor(Math.random() * tempList.length);
        var element = tempList.splice(randomIndex, 1)[0];
        result.push(element);
    }

    return result;
}
for (let i=0;i<alques.length;i++) {
    let sel=alques[i].getElementsByClassName("jqradio");
    if(sel.length<1)
    {
        muNow++;
        let choiced=getRandomUniqueElementsFromList(choice[i],multi[muNow]);
        sel=alques[i].getElementsByClassName("jqcheck");
        for (let j = 0; j < choiced.length; j++) {
            sel[choiced[j]].click();
        }
    }else {
        sel[getRandomElementFromList(choice[i])].click();
    }
}
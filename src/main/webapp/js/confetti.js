/*** 抽選時に放たれるクラッカー ***/


window.addEventListener('load', function () {

    // 中央付近から大きめにドカン
    confetti({
        particleCount: 180, 
        spread: 90,
        origin: { x: 0.5, y: 0.4 }
    });

    // 左
    confetti({
        particleCount: 120,
        spread: 70,
        origin: { x: 0.2, y: 0.5 }
    });

    // 右
    confetti({
        particleCount: 120,
        spread: 70,
        origin: { x: 0.8, y: 0.5 }
    });

});

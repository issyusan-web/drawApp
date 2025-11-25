/*** 終了メンバーが画面を動き回る ***/

window.addEventListener("load", () => {
    const area = document.querySelector(".finished-area");
    if (!area) return;

    const movers = area.querySelectorAll(".finished-mover");
    const areaWidth = area.clientWidth;
    const areaHeight = area.clientHeight;

    movers.forEach(mover => {
        // 初期位置（エリア内ランダム）
        let x = Math.random() * (areaWidth  - mover.offsetWidth);
        let y = Math.random() * (areaHeight - mover.offsetHeight);
        // 速度（ゆっくりめ）
        let dx = (Math.random() * 1.5) + 0.5;   // 0.5 ～ 2.0
        let dy = (Math.random() * 1.5) + 0.5;

        // 位置の初期反映
        mover.style.left = x + "px";
        mover.style.top  = y + "px";

        function move() {
            x += dx;
            y += dy;

            const w = mover.offsetWidth;
            const h = mover.offsetHeight;

            // 左右の壁
            if (x <= 0 || x + w >= areaWidth) {
                dx = -dx;
                x = Math.max(0, Math.min(x, areaWidth - w));
            }

            // 上下の壁
            if (y <= 0 || y + h >= areaHeight) {
                dy = -dy;
                y = Math.max(0, Math.min(y, areaHeight - h));
            }

            mover.style.left = x + "px";
            mover.style.top  = y + "px";

            requestAnimationFrame(move);
        }

        // position は CSS の absolute をそのまま使う
        mover.style.position = "absolute";
        mover.style.zIndex = "1";

        move();
    });
});

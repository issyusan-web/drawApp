// /js/pass.js

// 複数行対応版（一覧画面・マイページ共通）
function togglePassword(button) {
    const input = button.previousElementSibling; // 同じセル内の input 取得
    if (!input) return;

    if (input.type === "password") {
        input.type = "text";
        button.textContent = "非表示";
    } else {
        input.type = "password";
        button.textContent = "表示";
    }
}

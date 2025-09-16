export function toast(message) {
    const toast = document.createElement("div");
    toast.className = "toast-message"

    toast.innerHTML = `
        <div class="toast-message__content toast-message-content">
            <span class="toast-message-txt">${message}</span>
        </div>
        
        <button class="toast-message__toast-close-btn toast-close-btn">✖</button>`;
    document.body.appendChild(toast);

    const closeBtn = toast.querySelector('.toast-close-btn');
    closeBtn.addEventListener('click', () => hideToast(toast));

    setTimeout(() => toast.classList.add('show'), 500);
    setTimeout(() => hideToast(toast), 2500);
}

function hideToast(toast) {
    toast.classList.remove('show');
    setTimeout(() => toast.remove(), 300);
}
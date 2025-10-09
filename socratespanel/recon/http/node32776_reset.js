const forgotPasswordForm    = document.getElementById('forgot-password-form');
const resetPasswordForm     = document.getElementById('reset-password-form');
const alerts                = document.getElementById('alerts');

const flash = (message, level) => {
    alerts.innerHTML += `
        <div class="alert alert-${level}" role="alert">
            <button type="button" id="closeAlert" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>${message}</strong>
        </div>
    `;
    
    setTimeout(() => {
        document.getElementById('closeAlert').click();
    }, 3000);
};

forgotPasswordForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = document.getElementById('forgotEmail').value;

    fetch('/api/forgot-password', {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify({ email })
    })
    .then(resp => resp.json())
    .then(resp => {
        flash(resp.message, resp.success ? 'success' : 'danger');
        if (resp.iframe) output.innerHTML = resp.iframe;
    })
    .catch(error => {
        console.error('Error:', error);
        flash('An error occurred while processing your request.', 'danger');
    });
});

resetPasswordForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const email = document.getElementById('resetEmail').value;
    const token = document.getElementById('resetToken').value;
    const newPassword = document.getElementById('newPassword').value;

    fetch('/api/reset-password', {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json' 
        },
        body: JSON.stringify({ email, token, newPassword })
    })
    .then(resp => resp.json())
    .then(resp => {
        flash(resp.message, resp.success ? 'success' : 'danger');
        if (resp.success) window.location.href = '/login';
    })
    .catch(error => {
        console.error('Error:', error);
        flash('An error occurred while processing your request.', 'danger');
    });
});
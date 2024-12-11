describe('Caso de prueba: Validación de Credenciales del Usuario Secretaria', () => {
  it('Inicio de sesión', () => {
    cy.visit('http://127.0.0.1:5500/html/login.html');
    cy.wait(500);

    cy.get('input[id="username"]').should('be.visible').type("rubi");
    cy.get('input[id="password"]').should('be.visible').type("rubi");
    cy.screenshot('CY02.1')

    cy.get('button[id="formButton"]').should('be.visible').click();

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY02.2');
    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    cy.wait(1000);

    cy.screenshot('CY02.3')
  })
})
export function login(username, password) {
    cy.visit('http://127.0.0.1:5500/html/login.html');
      cy.wait(500);

      cy.get('input[id="username"]').should('be.visible').type(username);
      cy.get('input[id="password"]').should('be.visible').type(password);
      cy.get('button[id="formButton"]').should('be.visible').click();
      cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
      cy.wait(500);
}
  
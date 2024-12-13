import { login } from './utils.js';

describe('Caso de prueba: Asignar enfermera a cama', () => {
  it('Asignar enfermera a cama', () => {
    login('rubi', 'rubi');

    cy.get('#bedCards .card') 
            .contains('.card-title', 'P1 Cama04') 
            .parents('.card')                 
            .find('button[data-bs-target="#updateNurseModal"]')       
            .click(); 

    cy.get('select[id="regNurse"]').should('be.visible').select("Alana Najera Centeno");
    cy.screenshot('CY14.1');
    cy.get('button[onclick="updateInsertBed()"]').should('be.visible').click();

    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible');
    cy.screenshot('CY14.2');
    cy.get('button[class="swal2-confirm swal2-styled"]').should('be.visible').click();
    
    cy.wait(500).screenshot('CY14.3');

  });
});

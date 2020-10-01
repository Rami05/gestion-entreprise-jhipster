import { element, by, ElementFinder } from 'protractor';

export class COMMANDE_PRODUITComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-commande-produit div table .btn-danger'));
    title = element.all(by.css('jhi-commande-produit div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class COMMANDE_PRODUITUpdatePage {
    pageTitle = element(by.id('jhi-commande-produit-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateCreationInput = element(by.id('field_dateCreation'));
    dateModificationInput = element(by.id('field_dateModification'));
    etatSelect = element(by.id('field_etat'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDateCreationInput(dateCreation) {
        await this.dateCreationInput.sendKeys(dateCreation);
    }

    async getDateCreationInput() {
        return this.dateCreationInput.getAttribute('value');
    }

    async setDateModificationInput(dateModification) {
        await this.dateModificationInput.sendKeys(dateModification);
    }

    async getDateModificationInput() {
        return this.dateModificationInput.getAttribute('value');
    }

    async setEtatSelect(etat) {
        await this.etatSelect.sendKeys(etat);
    }

    async getEtatSelect() {
        return this.etatSelect.element(by.css('option:checked')).getText();
    }

    async etatSelectLastOption() {
        await this.etatSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class COMMANDE_PRODUITDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-cOMMANDE_PRODUIT-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-cOMMANDE_PRODUIT'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}

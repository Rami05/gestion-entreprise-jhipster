import { element, by, ElementFinder } from 'protractor';

export class CommandeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-commande div table .btn-danger'));
    title = element.all(by.css('jhi-commande div h2#page-heading span')).first();

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

export class CommandeUpdatePage {
    pageTitle = element(by.id('jhi-commande-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    referenceInput = element(by.id('field_reference'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setReferenceInput(reference) {
        await this.referenceInput.sendKeys(reference);
    }

    async getReferenceInput() {
        return this.referenceInput.getAttribute('value');
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

export class CommandeDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-commande-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-commande'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}

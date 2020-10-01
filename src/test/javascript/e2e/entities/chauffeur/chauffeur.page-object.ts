import { element, by, ElementFinder } from 'protractor';

export class ChauffeurComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-chauffeur div table .btn-danger'));
    title = element.all(by.css('jhi-chauffeur div h2#page-heading span')).first();

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

export class ChauffeurUpdatePage {
    pageTitle = element(by.id('jhi-chauffeur-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    identiteInput = element(by.id('field_identite'));
    cinInput = element(by.id('field_cin'));
    permis_de_conduitSelect = element(by.id('field_permis_de_conduit'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setIdentiteInput(identite) {
        await this.identiteInput.sendKeys(identite);
    }

    async getIdentiteInput() {
        return this.identiteInput.getAttribute('value');
    }

    async setCinInput(cin) {
        await this.cinInput.sendKeys(cin);
    }

    async getCinInput() {
        return this.cinInput.getAttribute('value');
    }

    async permis_de_conduitSelectLastOption() {
        await this.permis_de_conduitSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async permis_de_conduitSelectOption(option) {
        await this.permis_de_conduitSelect.sendKeys(option);
    }

    getPermis_de_conduitSelect(): ElementFinder {
        return this.permis_de_conduitSelect;
    }

    async getPermis_de_conduitSelectedOption() {
        return this.permis_de_conduitSelect.element(by.css('option:checked')).getText();
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

export class ChauffeurDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-chauffeur-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-chauffeur'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}

import { element, by, ElementFinder } from 'protractor';

export class Permis_de_conduitComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-permis-de-conduit div table .btn-danger'));
    title = element.all(by.css('jhi-permis-de-conduit div h2#page-heading span')).first();

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

export class Permis_de_conduitUpdatePage {
    pageTitle = element(by.id('jhi-permis-de-conduit-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    num_permisInput = element(by.id('field_num_permis'));
    copie_permisInput = element(by.id('file_copie_permis'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNum_permisInput(num_permis) {
        await this.num_permisInput.sendKeys(num_permis);
    }

    async getNum_permisInput() {
        return this.num_permisInput.getAttribute('value');
    }

    async setCopie_permisInput(copie_permis) {
        await this.copie_permisInput.sendKeys(copie_permis);
    }

    async getCopie_permisInput() {
        return this.copie_permisInput.getAttribute('value');
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

export class Permis_de_conduitDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-permis_de_conduit-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-permis_de_conduit'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}

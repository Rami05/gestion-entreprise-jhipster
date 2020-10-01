/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    Permis_de_conduitComponentsPage,
    Permis_de_conduitDeleteDialog,
    Permis_de_conduitUpdatePage
} from './permis-de-conduit.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('Permis_de_conduit e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let permis_de_conduitUpdatePage: Permis_de_conduitUpdatePage;
    let permis_de_conduitComponentsPage: Permis_de_conduitComponentsPage;
    let permis_de_conduitDeleteDialog: Permis_de_conduitDeleteDialog;
    const fileNameToUpload = 'logo-jhipster.png';
    const fileToUpload = '../../../../../main/webapp/content/images/' + fileNameToUpload;
    const absolutePath = path.resolve(__dirname, fileToUpload);

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Permis_de_conduits', async () => {
        await navBarPage.goToEntity('permis-de-conduit');
        permis_de_conduitComponentsPage = new Permis_de_conduitComponentsPage();
        await browser.wait(ec.visibilityOf(permis_de_conduitComponentsPage.title), 5000);
        expect(await permis_de_conduitComponentsPage.getTitle()).to.eq('jhipsterApp.permis_de_conduit.home.title');
    });

    it('should load create Permis_de_conduit page', async () => {
        await permis_de_conduitComponentsPage.clickOnCreateButton();
        permis_de_conduitUpdatePage = new Permis_de_conduitUpdatePage();
        expect(await permis_de_conduitUpdatePage.getPageTitle()).to.eq('jhipsterApp.permis_de_conduit.home.createOrEditLabel');
        await permis_de_conduitUpdatePage.cancel();
    });

    it('should create and save Permis_de_conduits', async () => {
        const nbButtonsBeforeCreate = await permis_de_conduitComponentsPage.countDeleteButtons();

        await permis_de_conduitComponentsPage.clickOnCreateButton();
        await promise.all([
            permis_de_conduitUpdatePage.setNum_permisInput('num_permis'),
            permis_de_conduitUpdatePage.setCopie_permisInput(absolutePath)
        ]);
        expect(await permis_de_conduitUpdatePage.getNum_permisInput()).to.eq('num_permis');
        expect(await permis_de_conduitUpdatePage.getCopie_permisInput()).to.endsWith(fileNameToUpload);
        await permis_de_conduitUpdatePage.save();
        expect(await permis_de_conduitUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await permis_de_conduitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Permis_de_conduit', async () => {
        const nbButtonsBeforeDelete = await permis_de_conduitComponentsPage.countDeleteButtons();
        await permis_de_conduitComponentsPage.clickOnLastDeleteButton();

        permis_de_conduitDeleteDialog = new Permis_de_conduitDeleteDialog();
        expect(await permis_de_conduitDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.permis_de_conduit.delete.question');
        await permis_de_conduitDeleteDialog.clickOnConfirmButton();

        expect(await permis_de_conduitComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});

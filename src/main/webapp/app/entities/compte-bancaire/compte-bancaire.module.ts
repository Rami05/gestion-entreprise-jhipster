import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
    Compte_bancaireComponent,
    Compte_bancaireDetailComponent,
    Compte_bancaireUpdateComponent,
    Compte_bancaireDeletePopupComponent,
    Compte_bancaireDeleteDialogComponent,
    compte_bancaireRoute,
    compte_bancairePopupRoute
} from './';

const ENTITY_STATES = [...compte_bancaireRoute, ...compte_bancairePopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        Compte_bancaireComponent,
        Compte_bancaireDetailComponent,
        Compte_bancaireUpdateComponent,
        Compte_bancaireDeleteDialogComponent,
        Compte_bancaireDeletePopupComponent
    ],
    entryComponents: [
        Compte_bancaireComponent,
        Compte_bancaireUpdateComponent,
        Compte_bancaireDeleteDialogComponent,
        Compte_bancaireDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterCompte_bancaireModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}

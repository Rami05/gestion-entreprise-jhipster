import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterSharedModule } from 'app/shared';
import {
    Permis_de_conduitComponent,
    Permis_de_conduitDetailComponent,
    Permis_de_conduitUpdateComponent,
    Permis_de_conduitDeletePopupComponent,
    Permis_de_conduitDeleteDialogComponent,
    permis_de_conduitRoute,
    permis_de_conduitPopupRoute
} from './';

const ENTITY_STATES = [...permis_de_conduitRoute, ...permis_de_conduitPopupRoute];

@NgModule({
    imports: [JhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        Permis_de_conduitComponent,
        Permis_de_conduitDetailComponent,
        Permis_de_conduitUpdateComponent,
        Permis_de_conduitDeleteDialogComponent,
        Permis_de_conduitDeletePopupComponent
    ],
    entryComponents: [
        Permis_de_conduitComponent,
        Permis_de_conduitUpdateComponent,
        Permis_de_conduitDeleteDialogComponent,
        Permis_de_conduitDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterPermis_de_conduitModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}

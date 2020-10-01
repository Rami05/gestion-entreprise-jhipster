/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { COMMANDE_PRODUITComponent } from 'app/entities/commande-produit/commande-produit.component';
import { COMMANDE_PRODUITService } from 'app/entities/commande-produit/commande-produit.service';
import { COMMANDE_PRODUIT } from 'app/shared/model/commande-produit.model';

describe('Component Tests', () => {
    describe('COMMANDE_PRODUIT Management Component', () => {
        let comp: COMMANDE_PRODUITComponent;
        let fixture: ComponentFixture<COMMANDE_PRODUITComponent>;
        let service: COMMANDE_PRODUITService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [COMMANDE_PRODUITComponent],
                providers: []
            })
                .overrideTemplate(COMMANDE_PRODUITComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(COMMANDE_PRODUITComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(COMMANDE_PRODUITService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new COMMANDE_PRODUIT(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.cOMMANDE_PRODUITS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

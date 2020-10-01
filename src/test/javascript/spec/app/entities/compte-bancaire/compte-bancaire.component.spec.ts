/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { Compte_bancaireComponent } from 'app/entities/compte-bancaire/compte-bancaire.component';
import { Compte_bancaireService } from 'app/entities/compte-bancaire/compte-bancaire.service';
import { Compte_bancaire } from 'app/shared/model/compte-bancaire.model';

describe('Component Tests', () => {
    describe('Compte_bancaire Management Component', () => {
        let comp: Compte_bancaireComponent;
        let fixture: ComponentFixture<Compte_bancaireComponent>;
        let service: Compte_bancaireService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Compte_bancaireComponent],
                providers: []
            })
                .overrideTemplate(Compte_bancaireComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Compte_bancaireComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Compte_bancaireService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Compte_bancaire(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.compte_bancaires[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

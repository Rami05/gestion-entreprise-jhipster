/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { ChauffeurComponent } from 'app/entities/chauffeur/chauffeur.component';
import { ChauffeurService } from 'app/entities/chauffeur/chauffeur.service';
import { Chauffeur } from 'app/shared/model/chauffeur.model';

describe('Component Tests', () => {
    describe('Chauffeur Management Component', () => {
        let comp: ChauffeurComponent;
        let fixture: ComponentFixture<ChauffeurComponent>;
        let service: ChauffeurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [ChauffeurComponent],
                providers: []
            })
                .overrideTemplate(ChauffeurComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChauffeurComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChauffeurService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Chauffeur(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.chauffeurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

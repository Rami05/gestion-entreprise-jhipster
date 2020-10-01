/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { Permis_de_conduitComponent } from 'app/entities/permis-de-conduit/permis-de-conduit.component';
import { Permis_de_conduitService } from 'app/entities/permis-de-conduit/permis-de-conduit.service';
import { Permis_de_conduit } from 'app/shared/model/permis-de-conduit.model';

describe('Component Tests', () => {
    describe('Permis_de_conduit Management Component', () => {
        let comp: Permis_de_conduitComponent;
        let fixture: ComponentFixture<Permis_de_conduitComponent>;
        let service: Permis_de_conduitService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [Permis_de_conduitComponent],
                providers: []
            })
                .overrideTemplate(Permis_de_conduitComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Permis_de_conduitComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Permis_de_conduitService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Permis_de_conduit(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.permis_de_conduits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterTestModule } from '../../../test.module';
import { EmployeComponent } from 'app/entities/employe/employe.component';
import { EmployeService } from 'app/entities/employe/employe.service';
import { Employe } from 'app/shared/model/employe.model';

describe('Component Tests', () => {
    describe('Employe Management Component', () => {
        let comp: EmployeComponent;
        let fixture: ComponentFixture<EmployeComponent>;
        let service: EmployeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterTestModule],
                declarations: [EmployeComponent],
                providers: []
            })
                .overrideTemplate(EmployeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EmployeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EmployeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Employe(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.employes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

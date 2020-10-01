import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IContrat } from 'app/shared/model/contrat.model';

type EntityResponseType = HttpResponse<IContrat>;
type EntityArrayResponseType = HttpResponse<IContrat[]>;

@Injectable({ providedIn: 'root' })
export class ContratService {
    public resourceUrl = SERVER_API_URL + 'api/contrats';

    constructor(protected http: HttpClient) {}

    create(contrat: IContrat): Observable<EntityResponseType> {
        return this.http.post<IContrat>(this.resourceUrl, contrat, { observe: 'response' });
    }

    update(contrat: IContrat): Observable<EntityResponseType> {
        return this.http.put<IContrat>(this.resourceUrl, contrat, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IContrat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IContrat[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
